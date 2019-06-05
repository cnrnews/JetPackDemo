package tzht.jetpack.room;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import tzht.jetpack.dao.WordDao;
import tzht.jetpack.entity.Word;

@Database(entities = {Word.class},version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static volatile WordRoomDatabase INSTANCE;

    private static RoomDatabase.Callback callback=new RoomDatabase.Callback(

    ){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void>
    {
        private WordDao wordDao;
        public PopulateDbAsync(WordRoomDatabase  wordRoomDatabase) {
            this.wordDao=wordRoomDatabase.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAll();
            wordDao.insert(new Word("hello"));
            wordDao.insert(new Word("word"));
            return null;
        }
    }
    public static WordRoomDatabase getDatabas(final Application application)
    {
        if (INSTANCE==null)
        {
            synchronized (WordRoomDatabase.class){
                if (INSTANCE==null)
                {
                    // Create database
                    INSTANCE=Room.databaseBuilder(application.getApplicationContext(),
                            WordRoomDatabase.class,"word_database")
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WordDao wordDao();
}
