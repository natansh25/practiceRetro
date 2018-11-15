package infinity1087.android.com.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import infinity1087.android.com.myapplication.R;
import infinity1087.android.com.myapplication.model.Example;
import infinity1087.android.com.myapplication.model.Result;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.MyViewHolder> {

    List<Result> movie;
    private ListItemClickListner mOnClickListener;
    Context mContext;

    public interface ListItemClickListner {
        void onListItemClick(Result movieResults);
        void onButtonClick(int position);
    }

    public void setOnItemClickListner(ListItemClickListner onClickListener)
    {
        this.mOnClickListener=onClickListener;
    }

    public NameAdapter(Context context,List<Result> movie) {
        this.movie = movie;
        this.mContext=context;
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

        private Button btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_name = itemView.findViewById(R.id.txt_name);
            btn=itemView.findViewById(R.id.button);
            txt_item_name.setOnClickListener(this);
            btn.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            if (view==txt_item_name)
            {
                int adapterPos = getAdapterPosition();
                Result res = movie.get(adapterPos);
                mOnClickListener.onListItemClick(res);
            }
            if (view==btn)
            {
                Toast.makeText(mContext, "Button pressed", Toast.LENGTH_SHORT).show();
                mOnClickListener.onButtonClick(getAdapterPosition());


            }




        }
    }
}
