package tzht.jetpack.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    public String mWord;

    public Word(String mWord) {
        this.mWord = mWord;
    }

    public String getmWord() {
        return mWord;
    }
}
