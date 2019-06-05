package tzht.jetpack.viewmodel;

import android.app.Application;
import android.app.ListActivity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import tzht.jetpack.entity.Word;
import tzht.jetpack.repository.Repository;
import tzht.jetpack.room.WordRoomDatabase;

public class WordViewModel extends AndroidViewModel {

    private Repository repository;

    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }
    private LiveData<List<Word>>mAllWords;
    public WordViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        mAllWords=repository.getmAllDatas();
    }
    public void insert(Word word){
        repository.insert(word);
    }
}
