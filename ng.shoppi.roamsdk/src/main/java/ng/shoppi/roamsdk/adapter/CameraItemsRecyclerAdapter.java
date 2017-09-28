package ng.shoppi.roamsdk.adapter;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import com.squareup.picasso.Picasso;

import java.io.File;

import ng.shoppi.roamsdk.R;
import ng.shoppi.roamsdk.model.MCamera;


/**
 * Created by Akinola on 10/1/2016.
 */
public class CameraItemsRecyclerAdapter extends RecyclerView.Adapter<CameraItemsRecyclerAdapter.RecyclerViewHolder> {
    private Context context;
    private MCamera mCamera;

    public CameraItemsRecyclerAdapter(Context paramContext, MCamera mCamera) {
        context = paramContext;
        this.mCamera = mCamera;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_grid_item, parent, false);
        return new RecyclerViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        File f = new File(mCamera.getImageList().get(position));
        if (f.exists()) {
            Uri uri = Uri.fromFile(f);
            Picasso.with(this.context)
                    .load(uri)
                    .into(holder.imgPicture);
        }

        holder.imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCamera.getImageList().remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mCamera.getImageList().size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        ImageView imgPicture;
        ImageView imageViewClose;
        Context mcontext;

        public RecyclerViewHolder(View itemView, Context context) {
            super(itemView);
            this.mcontext = context;
            imgPicture = (ImageView) itemView.findViewById(R.id.imgPicture);
            imageViewClose = (ImageView) itemView.findViewById(R.id.imageViewClose);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void onClick(View paramView) {
            int i = getAdapterPosition();
            //Log.e("hshas", "We clicked: " + documentObject.getCollections().get(i).getPix());
            ShowFullImageDialog(i);
        }

        public boolean onLongClick(View paramView) {
            int i = getAdapterPosition();
            ShowContextMenu(i);
            return true;
        }

        private void ShowContextMenu(final int pos) {

        }


        int currentImage;
        ImageSwitcherPicasso mImageSwitcherPicasso;
        Dialog dialog;

        private void ShowFullImageDialog(int index) {
            currentImage = index;
            final AlertDialog.Builder sdialog = new AlertDialog.Builder(context);
            //dialog = new Dialog(context);
            // Set the title
            //dialog.setTitle(documentObject.getCollections().get(currentImage).getPix());
            //sdialog.setMessage(documentObject.getCollections().get(currentImage).getPix());

            View v = View.inflate(context, R.layout.pictures_preview, null);
            sdialog.setView(v);


            ImageView prev = (ImageView) v.findViewById(R.id.btnPrevious);
            ImageView next = (ImageView) v.findViewById(R.id.btnNext);

            final ImageSwitcher switcher = (ImageSwitcher) v.findViewById(R.id.imageSwitcher);
            switcher.setFactory(new ViewSwitcher.ViewFactory() {
                @Override
                public View makeView() {
                    ImageView myView = new ImageView(context);
                    myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    myView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    return myView;
                }
            });

            mImageSwitcherPicasso = new ImageSwitcherPicasso(context, switcher);

            if (currentImage >= 0 && currentImage < getItemCount()) {
                File f = new File(mCamera.getImageList().get(currentImage));
                if (f.exists()) {
                    Log.e("hshas", "file existssds");
                    Uri uri = Uri.fromFile(f);
                    Picasso.with(context)
                            .load(uri)
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.no_image_available)
                            .into(mImageSwitcherPicasso);
                }

            }

            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //switcher.setImageResource(R.drawable.document);
                    if (currentImage > 0) {
                        currentImage--;
                        File f = new File(mCamera.getImageList().get(currentImage));
                        if (f.exists()) {
                            Uri uri = Uri.fromFile(f);
                            Picasso.with(context)
                                    .load(uri)
                                    .placeholder(R.drawable.placeholder)
                                    .error(R.drawable.no_image_available)
                                    .into(mImageSwitcherPicasso);
                        }

                    }
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //switcher.setImageResource(R.drawable.help4);

                    if (currentImage < getItemCount() - 1) {
                        currentImage++;
                        File f = new File(mCamera.getImageList().get(currentImage));
                        if (f.exists()) {
                            Uri uri = Uri.fromFile(f);
                            Picasso.with(context)
                                    .load(uri)
                                    .placeholder(R.drawable.placeholder)
                                    .error(R.drawable.no_image_available)
                                    .into(mImageSwitcherPicasso);
                        }
                    }
                }
            });

            dialog = sdialog.create();
            // Display the dialog
            dialog.show();
        }
    }
}
