package infinity1087.android.com.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import infinity1087.android.com.myapplication.R;
import infinity1087.android.com.myapplication.model.Example;
import infinity1087.android.com.myapplication.model.Result;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.MyViewHolder> {

    List<Result> movie;
    final private ListItemClickListner mOnClickListener;

    public interface ListItemClickListner {
        void onListItemClick(Result movieResults);
    }

    public NameAdapter(List<Result> movie, ListItemClickListner onClickListener) {
        this.movie = movie;
        mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Result example = movie.get(i);
        myViewHolder.txt_item_name.setText(example.getTitle());


    }

    @Override
    public int getItemCount() {
        return movie.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txt_item_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_name = itemView.findViewById(R.id.txt_name);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            int adapterPos = getAdapterPosition();
            Result res = movie.get(adapterPos);
            mOnClickListener.onListItemClick(res);


        }
    }
}
