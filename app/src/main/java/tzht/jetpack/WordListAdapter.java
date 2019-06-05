package tzht.jetpack;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tzht.jetpack.entity.Word;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {


    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words
    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    public WordListAdapter(Context context) {
        mInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View contentView=mInflater.inflate(R.layout.recyclerview_item,
                parent,false);
        return new WordViewHolder(contentView);

    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords!=null)
        {
            holder.wordItemView.setText(mWords.get(position).getmWord());
        }else{
            holder.wordItemView.setText("no word");
        }
    }

    public void setWords(List<Word> mWords) {
        this.mWords = mWords;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWords!=null) {
            return mWords.size();
        }else{
            return 0;
        }
    }
}
