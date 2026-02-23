package com.ranesvision.app.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.ranesvision.app.data.local.dao.ImageDao;
import com.ranesvision.app.data.local.dao.ImageDao_Impl;
import com.ranesvision.app.data.local.dao.LogItDao;
import com.ranesvision.app.data.local.dao.LogItDao_Impl;
import com.ranesvision.app.data.local.dao.TagDao;
import com.ranesvision.app.data.local.dao.TagDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ImageDao _imageDao;

  private volatile TagDao _tagDao;

  private volatile LogItDao _logItDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `images` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `filePath` TEXT NOT NULL, `thumbnailPath` TEXT, `timestamp` INTEGER NOT NULL, `width` INTEGER NOT NULL, `height` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `tags` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `image_tag_cross_ref` (`imageId` INTEGER NOT NULL, `tagId` INTEGER NOT NULL, PRIMARY KEY(`imageId`, `tagId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `logit_entries` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `timestamp` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `logit_images` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `entryId` INTEGER NOT NULL, `imagePath` TEXT NOT NULL, `comment` TEXT NOT NULL, `sortOrder` INTEGER NOT NULL, FOREIGN KEY(`entryId`) REFERENCES `logit_entries`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_logit_images_entryId` ON `logit_images` (`entryId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '195328f35afac37521e0d720099db056')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `images`");
        db.execSQL("DROP TABLE IF EXISTS `tags`");
        db.execSQL("DROP TABLE IF EXISTS `image_tag_cross_ref`");
        db.execSQL("DROP TABLE IF EXISTS `logit_entries`");
        db.execSQL("DROP TABLE IF EXISTS `logit_images`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsImages = new HashMap<String, TableInfo.Column>(6);
        _columnsImages.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImages.put("filePath", new TableInfo.Column("filePath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImages.put("thumbnailPath", new TableInfo.Column("thumbnailPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImages.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImages.put("width", new TableInfo.Column("width", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImages.put("height", new TableInfo.Column("height", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysImages = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesImages = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoImages = new TableInfo("images", _columnsImages, _foreignKeysImages, _indicesImages);
        final TableInfo _existingImages = TableInfo.read(db, "images");
        if (!_infoImages.equals(_existingImages)) {
          return new RoomOpenHelper.ValidationResult(false, "images(com.ranesvision.app.data.local.entity.ImageEntity).\n"
                  + " Expected:\n" + _infoImages + "\n"
                  + " Found:\n" + _existingImages);
        }
        final HashMap<String, TableInfo.Column> _columnsTags = new HashMap<String, TableInfo.Column>(2);
        _columnsTags.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTags.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTags = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTags = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTags = new TableInfo("tags", _columnsTags, _foreignKeysTags, _indicesTags);
        final TableInfo _existingTags = TableInfo.read(db, "tags");
        if (!_infoTags.equals(_existingTags)) {
          return new RoomOpenHelper.ValidationResult(false, "tags(com.ranesvision.app.data.local.entity.TagEntity).\n"
                  + " Expected:\n" + _infoTags + "\n"
                  + " Found:\n" + _existingTags);
        }
        final HashMap<String, TableInfo.Column> _columnsImageTagCrossRef = new HashMap<String, TableInfo.Column>(2);
        _columnsImageTagCrossRef.put("imageId", new TableInfo.Column("imageId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImageTagCrossRef.put("tagId", new TableInfo.Column("tagId", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysImageTagCrossRef = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesImageTagCrossRef = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoImageTagCrossRef = new TableInfo("image_tag_cross_ref", _columnsImageTagCrossRef, _foreignKeysImageTagCrossRef, _indicesImageTagCrossRef);
        final TableInfo _existingImageTagCrossRef = TableInfo.read(db, "image_tag_cross_ref");
        if (!_infoImageTagCrossRef.equals(_existingImageTagCrossRef)) {
          return new RoomOpenHelper.ValidationResult(false, "image_tag_cross_ref(com.ranesvision.app.data.local.entity.ImageTagCrossRef).\n"
                  + " Expected:\n" + _infoImageTagCrossRef + "\n"
                  + " Found:\n" + _existingImageTagCrossRef);
        }
        final HashMap<String, TableInfo.Column> _columnsLogitEntries = new HashMap<String, TableInfo.Column>(3);
        _columnsLogitEntries.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogitEntries.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogitEntries.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLogitEntries = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLogitEntries = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLogitEntries = new TableInfo("logit_entries", _columnsLogitEntries, _foreignKeysLogitEntries, _indicesLogitEntries);
        final TableInfo _existingLogitEntries = TableInfo.read(db, "logit_entries");
        if (!_infoLogitEntries.equals(_existingLogitEntries)) {
          return new RoomOpenHelper.ValidationResult(false, "logit_entries(com.ranesvision.app.data.local.entity.LogItEntryEntity).\n"
                  + " Expected:\n" + _infoLogitEntries + "\n"
                  + " Found:\n" + _existingLogitEntries);
        }
        final HashMap<String, TableInfo.Column> _columnsLogitImages = new HashMap<String, TableInfo.Column>(5);
        _columnsLogitImages.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogitImages.put("entryId", new TableInfo.Column("entryId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogitImages.put("imagePath", new TableInfo.Column("imagePath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogitImages.put("comment", new TableInfo.Column("comment", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogitImages.put("sortOrder", new TableInfo.Column("sortOrder", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLogitImages = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysLogitImages.add(new TableInfo.ForeignKey("logit_entries", "CASCADE", "NO ACTION", Arrays.asList("entryId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesLogitImages = new HashSet<TableInfo.Index>(1);
        _indicesLogitImages.add(new TableInfo.Index("index_logit_images_entryId", false, Arrays.asList("entryId"), Arrays.asList("ASC")));
        final TableInfo _infoLogitImages = new TableInfo("logit_images", _columnsLogitImages, _foreignKeysLogitImages, _indicesLogitImages);
        final TableInfo _existingLogitImages = TableInfo.read(db, "logit_images");
        if (!_infoLogitImages.equals(_existingLogitImages)) {
          return new RoomOpenHelper.ValidationResult(false, "logit_images(com.ranesvision.app.data.local.entity.LogItImageEntity).\n"
                  + " Expected:\n" + _infoLogitImages + "\n"
                  + " Found:\n" + _existingLogitImages);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "195328f35afac37521e0d720099db056", "54df0e991031c43cdac8a37729b0e9ec");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "images","tags","image_tag_cross_ref","logit_entries","logit_images");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `images`");
      _db.execSQL("DELETE FROM `tags`");
      _db.execSQL("DELETE FROM `image_tag_cross_ref`");
      _db.execSQL("DELETE FROM `logit_entries`");
      _db.execSQL("DELETE FROM `logit_images`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ImageDao.class, ImageDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TagDao.class, TagDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LogItDao.class, LogItDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ImageDao imageDao() {
    if (_imageDao != null) {
      return _imageDao;
    } else {
      synchronized(this) {
        if(_imageDao == null) {
          _imageDao = new ImageDao_Impl(this);
        }
        return _imageDao;
      }
    }
  }

  @Override
  public TagDao tagDao() {
    if (_tagDao != null) {
      return _tagDao;
    } else {
      synchronized(this) {
        if(_tagDao == null) {
          _tagDao = new TagDao_Impl(this);
        }
        return _tagDao;
      }
    }
  }

  @Override
  public LogItDao logItDao() {
    if (_logItDao != null) {
      return _logItDao;
    } else {
      synchronized(this) {
        if(_logItDao == null) {
          _logItDao = new LogItDao_Impl(this);
        }
        return _logItDao;
      }
    }
  }
}
