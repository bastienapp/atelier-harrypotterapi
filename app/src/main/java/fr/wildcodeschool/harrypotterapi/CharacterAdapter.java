package fr.wildcodeschool.harrypotterapi;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<Character> mCharacters;
    private Context mContext;

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imageView;
        public CharacterViewHolder(ConstraintLayout v) {
            super(v);
            imageView = v.findViewById(R.id.ivCharacter);
        }
    }

    public CharacterAdapter(List<Character> characters, Context context) {
        mCharacters = characters;
        mContext = context;
    }

    @Override
    public CharacterAdapter.CharacterViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_character, parent, false);

        return new CharacterViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Character character = mCharacters.get(position);

        Glide.with(mContext)
                .load(character.getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }
}
