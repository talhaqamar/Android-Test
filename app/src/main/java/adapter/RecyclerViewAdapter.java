package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.talha.test.R;

import java.util.List;

import models.FactsVO;

/**
 * Created by talhaqamar on 27/3/18.
 */


/**
 * This class is the adapter for the recycler view
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
   private List<FactsVO> mDataset;
   Context context;

   // Provide a reference to the views for each data item
   // Complex data items may need more than one view per item, and
   // you provide access to all the views for a data item in a view holder
   public class ViewHolder extends RecyclerView.ViewHolder {
	  // each data item is just a string in this case
	  public TextView title,description;
	  public ImageView thumbnail;

	  public ViewHolder(View v) {
		 super(v);
		 title = (TextView) v.findViewById(R.id.title);
		 description = (TextView) v.findViewById(R.id.description);
		 thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
	  }
   }

   public void add(int position, FactsVO item) {
	  mDataset.add(position, item);
	  notifyItemInserted(position);
   }

   public void remove(String item) {
	  int position = mDataset.indexOf(item);
	  mDataset.remove(position);
	  notifyItemRemoved(position);
   }

   public RecyclerViewAdapter(Context context, List<FactsVO> myDataset) {
	  mDataset = myDataset;
	  this.context = context;
   }

   @Override
   public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listinnerview, parent, false);
	  RecyclerViewAdapter.ViewHolder vh = new RecyclerViewAdapter.ViewHolder(v);
	  return vh;
   }

   // Replace the contents of a view (invoked by the layout manager)
   @Override
   public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {
	  final FactsVO fact = mDataset.get(position);
	  holder.title.setText(fact.getTitle());
	  holder.description.setText(fact.getDescription());

	  Picasso.with(context)
			  .load(fact.getImageHref())
			  .placeholder(R.drawable.ic_launcher_background)

			  .into(holder.thumbnail);

   }

   // Return the size of your dataset (invoked by the layout manager)
   @Override
   public int getItemCount() {
	  return mDataset.size();
   }

}