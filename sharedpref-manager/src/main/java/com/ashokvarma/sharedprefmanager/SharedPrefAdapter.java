package com.ashokvarma.sharedprefmanager;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/**
 * Class description
 *
 * @author ashokvarma
 * @version 1.0
 * @see RecyclerView.Adapter
 * @since 22 Jun 2017
 */
class SharedPrefAdapter extends RecyclerView.Adapter<SharedPrefAdapter.ViewHolder> {

    private ArrayList<SharedPrefItemModel> mSharedPrefItemModelList = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private Listener mListener;


    SharedPrefAdapter(Context context, ArrayList<SharedPrefItemModel> sharedPrefItemModels) {
        if (sharedPrefItemModels != null) {
            this.mSharedPrefItemModelList.addAll(sharedPrefItemModels);
        }
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.sharedpref_manager_recycler_item, parent, false);
        return new ViewHolder(v);
    }

    void setSharedPrefItemModelList(ArrayList<SharedPrefItemModel> sharedPrefItemModels) {
        this.mSharedPrefItemModelList.clear();
        if (sharedPrefItemModels != null) {
            this.mSharedPrefItemModelList.addAll(sharedPrefItemModels);
        }
        notifyDataSetChanged();
    }

    void setListener(Listener listener) {
        this.mListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vh, int position) {
        vh.textView.setText(mSharedPrefItemModelList.get(position).getDisplayText());
        vh.textViewTitle.setText(mSharedPrefItemModelList.get(position).getTitleText());
    }

    @Override
    public int getItemCount() {
        return mSharedPrefItemModelList == null ? 0 : mSharedPrefItemModelList.size();
    }

    interface Listener {
        void onSharedPrefItemClicked(SharedPrefItemModel sharedPrefItemModel);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textViewTitle;
        ImageView imageArrow;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
            textViewTitle = itemView.findViewById(R.id.item_title_text);

            textViewTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (textView.getVisibility() == View.GONE) {
                        textViewTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.sharedpref_arrow_down, 0);
                        textView.setVisibility(View.VISIBLE);
                    } else {
                        textViewTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.sharedpref_arrow_up, 0);
                        textView.setVisibility(View.GONE);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onSharedPrefItemClicked(mSharedPrefItemModelList.get(getLayoutPosition()));
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                    if (clipboard != null) {
                        String clipText = mSharedPrefItemModelList.get(getLayoutPosition()).getValue().toString();

                        ClipData clip;
                        clip = ClipData.newPlainText("com.ashokvarma.sharedprefmanager", clipText);

                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(mContext, clipText + " - Copied to clipBoard", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
        }
    }
}