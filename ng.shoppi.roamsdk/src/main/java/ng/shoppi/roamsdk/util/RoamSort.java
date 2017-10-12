package ng.shoppi.roamsdk.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ng.shoppi.roamsdk.model.Address;
import ng.shoppi.roamsdk.model.Job;

/**
 * Created by Akinola on 10/12/2017.
 * This is the Roam RoamSort Class for sorting collections by different criteria
 */

public class RoamSort {

    public RoamSort() {
    }


    public enum Sort {
        ASC, DESC
    }

    public ArrayList<Job> sortJobsByState(ArrayList<Job> jobs, final Sort sort) throws NullPointerException {

        if (jobs == null) {
            throw new NullPointerException("jobs is null");
        }

        for (Job job : jobs) {
            ArrayList<Address> addresses = job.getAddress();
            Collections.sort(addresses, new Comparator<Address>() {
                @Override
                public int compare(Address address1, Address address2) {
                    if (sort == Sort.ASC) {
                        return address1.getTown().getState().getName().compareTo(address2.getTown().getState().getName());
                    } else {
                        return address2.getTown().getState().getName().compareTo(address1.getTown().getState().getName());
                    }
                }
            });
        }


        Collections.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job job1, Job job2) {
                if (sort == Sort.ASC) {
                    if (job1.getAddress().size() == 0) {
                        return -1;
                    } else if (job2.getAddress().size() == 0) {
                        return 1;
                    } else {
                        return job1.getAddress().get(0).getTown().getState().getName().compareTo(job2.getAddress().get(0).getTown().getState().getName());
                    }
                } else {
                    if (job1.getAddress().size() == 0) {
                        return 1;
                    } else if (job2.getAddress().size() == 0) {
                        return -1;
                    } else {
                        return job2.getAddress().get(0).getTown().getState().getName().compareTo(job1.getAddress().get(0).getTown().getState().getName());
                    }
                }
            }
        });

        return jobs;
    }

    public ArrayList<Job> sortJobsByTown(ArrayList<Job> jobs, final Sort sort) throws NullPointerException {

        if (jobs == null) {
            throw new NullPointerException("jobs is null");
        }

        for (Job job : jobs) {
            ArrayList<Address> addresses = job.getAddress();
            Collections.sort(addresses, new Comparator<Address>() {
                @Override
                public int compare(Address address1, Address address2) {
                    if (sort == Sort.ASC) {
                        return address1.getTown().getName().compareTo(address2.getTown().getName());
                    } else {
                        return address2.getTown().getName().compareTo(address1.getTown().getName());
                    }
                }
            });
        }

        Collections.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job job1, Job job2) {
                if (sort == Sort.ASC) {
                    if (job1.getAddress().size() == 0) {
                        return -1;
                    } else if (job2.getAddress().size() == 0) {
                        return 1;
                    } else {
                        return job1.getAddress().get(0).getTown().getName().compareTo(job2.getAddress().get(0).getTown().getName());
                    }
                } else {
                    if (job1.getAddress().size() == 0) {
                        return 1;
                    } else if (job2.getAddress().size() == 0) {
                        return -1;
                    } else {
                        return job2.getAddress().get(0).getTown().getName().compareTo(job1.getAddress().get(0).getTown().getName());
                    }
                }
            }
        });

        return jobs;
    }

    public ArrayList<Job> sortJobsByWage(ArrayList<Job> jobs, final Sort sort) throws NullPointerException {
        if (jobs == null) {
            throw new NullPointerException("jobs is null");
        }

        Collections.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job job1, Job job2) {
                if (sort == Sort.ASC) {
                    if (job1.getWage() < job2.getWage()) {
                        return -1;
                    } else if (job1.getWage() > job2.getWage()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } else {
                    if (job1.getWage() > job2.getWage()) {
                        return -1;
                    } else if (job1.getWage() < job2.getWage()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });

        return jobs;
    }

    public ArrayList<Job> sortJobsByProximity(final double userLatitude, final double userLongitude, ArrayList<Job> jobs, final Sort sort) throws NullPointerException {
        if (jobs == null) {
            throw new NullPointerException("jobs is null");
        }

        for (Job job : jobs) {
            ArrayList<Address> addresses = job.getAddress();
            Collections.sort(addresses, new Comparator<Address>() {
                @Override
                public int compare(Address address1, Address address2) {
                    if (sort == Sort.ASC) {
                        if (address1.getDistanceFrom(userLatitude, userLongitude) < address2.getDistanceFrom(userLatitude, userLongitude)) {
                            return -1;
                        } else if (address1.getDistanceFrom(userLatitude, userLongitude) > address2.getDistanceFrom(userLatitude, userLongitude)) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } else {
                        if (address1.getDistanceFrom(userLatitude, userLongitude) > address2.getDistanceFrom(userLatitude, userLongitude)) {
                            return -1;
                        } else if (address1.getDistanceFrom(userLatitude, userLongitude) < address2.getDistanceFrom(userLatitude, userLongitude)) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
            });
        }

        Collections.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job job1, Job job2) {
                if (sort == Sort.ASC) {
                    if (job1.getAddress().size() == 0) {
                        return -1;
                    } else if (job2.getAddress().size() == 0) {
                        return 1;
                    } else {
                        if (job1.getAddress().get(0).getDistanceFrom(userLatitude, userLongitude) < job2.getAddress().get(0).getDistanceFrom(userLatitude, userLongitude)) {
                            return -1;
                        } else if (job1.getAddress().get(0).getDistanceFrom(userLatitude, userLongitude) > job2.getAddress().get(0).getDistanceFrom(userLatitude, userLongitude)) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                } else {
                    if (job1.getAddress().size() == 0) {
                        return 1;
                    } else if (job2.getAddress().size() == 0) {
                        return -1;
                    } else {
                        if (job1.getAddress().get(0).getDistanceFrom(userLatitude, userLongitude) > job2.getAddress().get(0).getDistanceFrom(userLatitude, userLongitude)) {
                            return -1;
                        } else if (job1.getAddress().get(0).getDistanceFrom(userLatitude, userLongitude) < job2.getAddress().get(0).getDistanceFrom(userLatitude, userLongitude)) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        });

        return jobs;
    }


}
