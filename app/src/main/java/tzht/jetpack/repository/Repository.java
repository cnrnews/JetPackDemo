package tzht.jetpack.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import tzht.jetpack.dao.WordDao;
import tzht.jetpack.entity.Word;
import tzht.jetpack.room.WordRoomDatabase;

public class Repository {
    private WordDao wordDao;

    public LiveData<List<Word>> getmAllDatas() {
        return mAllDatas;
    }

    private LiveData<List<Word>>mAllDatas;

    public Repository(Application application) {
        WordRoomDatabase db=WordRoomDatabase.getDatabas(application);
        wordDao=db.wordDao();
        mAllDatas=wordDao.getAllWords();
    }
    public void insert(Word word)
    {
        new InsertAsyncTask(wordDao).execute(word);
    }
    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        InsertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Word... params) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mAsyncTaskDao.insert(params[0]);
                }
            }).start();
            return null;
        }
    }
}
