package com.ranesvision.app.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.ranesvision.app.data.local.entity.LogItEntryEntity;
import com.ranesvision.app.data.local.entity.LogItEntryWithImages;
import com.ranesvision.app.data.local.entity.LogItImageEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class LogItDao_Impl implements LogItDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LogItEntryEntity> __insertionAdapterOfLogItEntryEntity;

  private final EntityInsertionAdapter<LogItImageEntity> __insertionAdapterOfLogItImageEntity;

  private final EntityDeletionOrUpdateAdapter<LogItEntryEntity> __deletionAdapterOfLogItEntryEntity;

  private final EntityDeletionOrUpdateAdapter<LogItImageEntity> __deletionAdapterOfLogItImageEntity;

  private final EntityDeletionOrUpdateAdapter<LogItEntryEntity> __updateAdapterOfLogItEntryEntity;

  private final EntityDeletionOrUpdateAdapter<LogItImageEntity> __updateAdapterOfLogItImageEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteImageById;

  public LogItDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLogItEntryEntity = new EntityInsertionAdapter<LogItEntryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `logit_entries` (`id`,`name`,`timestamp`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LogItEntryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindLong(3, entity.getTimestamp());
      }
    };
    this.__insertionAdapterOfLogItImageEntity = new EntityInsertionAdapter<LogItImageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `logit_images` (`id`,`entryId`,`imagePath`,`comment`,`sortOrder`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LogItImageEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getEntryId());
        statement.bindString(3, entity.getImagePath());
        statement.bindString(4, entity.getComment());
        statement.bindLong(5, entity.getSortOrder());
      }
    };
    this.__deletionAdapterOfLogItEntryEntity = new EntityDeletionOrUpdateAdapter<LogItEntryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `logit_entries` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LogItEntryEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfLogItImageEntity = new EntityDeletionOrUpdateAdapter<LogItImageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `logit_images` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LogItImageEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfLogItEntryEntity = new EntityDeletionOrUpdateAdapter<LogItEntryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `logit_entries` SET `id` = ?,`name` = ?,`timestamp` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LogItEntryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindLong(3, entity.getTimestamp());
        statement.bindLong(4, entity.getId());
      }
    };
    this.__updateAdapterOfLogItImageEntity = new EntityDeletionOrUpdateAdapter<LogItImageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `logit_images` SET `id` = ?,`entryId` = ?,`imagePath` = ?,`comment` = ?,`sortOrder` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LogItImageEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getEntryId());
        statement.bindString(3, entity.getImagePath());
        statement.bindString(4, entity.getComment());
        statement.bindLong(5, entity.getSortOrder());
        statement.bindLong(6, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteImageById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM logit_images WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertEntry(final LogItEntryEntity entry,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfLogItEntryEntity.insertAndReturnId(entry);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertImage(final LogItImageEntity image,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfLogItImageEntity.insertAndReturnId(image);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteEntry(final LogItEntryEntity entry,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfLogItEntryEntity.handle(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteImage(final LogItImageEntity image,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfLogItImageEntity.handle(image);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateEntry(final LogItEntryEntity entry,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfLogItEntryEntity.handle(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateImage(final LogItImageEntity image,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfLogItImageEntity.handle(image);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteImageById(final long imageId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteImageById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, imageId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteImageById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<LogItEntryWithImages>> getAllEntries() {
    final String _sql = "SELECT * FROM logit_entries ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"logit_images",
        "logit_entries"}, new Callable<List<LogItEntryWithImages>>() {
      @Override
      @NonNull
      public List<LogItEntryWithImages> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
            final LongSparseArray<ArrayList<LogItImageEntity>> _collectionImages = new LongSparseArray<ArrayList<LogItImageEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionImages.containsKey(_tmpKey)) {
                _collectionImages.put(_tmpKey, new ArrayList<LogItImageEntity>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshiplogitImagesAscomRanesvisionAppDataLocalEntityLogItImageEntity(_collectionImages);
            final List<LogItEntryWithImages> _result = new ArrayList<LogItEntryWithImages>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final LogItEntryWithImages _item;
              final LogItEntryEntity _tmpEntry;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final String _tmpName;
              _tmpName = _cursor.getString(_cursorIndexOfName);
              final long _tmpTimestamp;
              _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
              _tmpEntry = new LogItEntryEntity(_tmpId,_tmpName,_tmpTimestamp);
              final ArrayList<LogItImageEntity> _tmpImagesCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpImagesCollection = _collectionImages.get(_tmpKey_1);
              _item = new LogItEntryWithImages(_tmpEntry,_tmpImagesCollection);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<LogItEntryWithImages>> searchEntries(final String query) {
    final String _sql = "SELECT * FROM logit_entries WHERE name LIKE '%' || ? || '%' ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"logit_images",
        "logit_entries"}, new Callable<List<LogItEntryWithImages>>() {
      @Override
      @NonNull
      public List<LogItEntryWithImages> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
            final LongSparseArray<ArrayList<LogItImageEntity>> _collectionImages = new LongSparseArray<ArrayList<LogItImageEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionImages.containsKey(_tmpKey)) {
                _collectionImages.put(_tmpKey, new ArrayList<LogItImageEntity>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshiplogitImagesAscomRanesvisionAppDataLocalEntityLogItImageEntity(_collectionImages);
            final List<LogItEntryWithImages> _result = new ArrayList<LogItEntryWithImages>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final LogItEntryWithImages _item;
              final LogItEntryEntity _tmpEntry;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final String _tmpName;
              _tmpName = _cursor.getString(_cursorIndexOfName);
              final long _tmpTimestamp;
              _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
              _tmpEntry = new LogItEntryEntity(_tmpId,_tmpName,_tmpTimestamp);
              final ArrayList<LogItImageEntity> _tmpImagesCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpImagesCollection = _collectionImages.get(_tmpKey_1);
              _item = new LogItEntryWithImages(_tmpEntry,_tmpImagesCollection);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getEntryById(final long entryId,
      final Continuation<? super LogItEntryWithImages> $completion) {
    final String _sql = "SELECT * FROM logit_entries WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, entryId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, true, _cancellationSignal, new Callable<LogItEntryWithImages>() {
      @Override
      @Nullable
      public LogItEntryWithImages call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
            final LongSparseArray<ArrayList<LogItImageEntity>> _collectionImages = new LongSparseArray<ArrayList<LogItImageEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionImages.containsKey(_tmpKey)) {
                _collectionImages.put(_tmpKey, new ArrayList<LogItImageEntity>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshiplogitImagesAscomRanesvisionAppDataLocalEntityLogItImageEntity(_collectionImages);
            final LogItEntryWithImages _result;
            if (_cursor.moveToFirst()) {
              final LogItEntryEntity _tmpEntry;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final String _tmpName;
              _tmpName = _cursor.getString(_cursorIndexOfName);
              final long _tmpTimestamp;
              _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
              _tmpEntry = new LogItEntryEntity(_tmpId,_tmpName,_tmpTimestamp);
              final ArrayList<LogItImageEntity> _tmpImagesCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpImagesCollection = _collectionImages.get(_tmpKey_1);
              _result = new LogItEntryWithImages(_tmpEntry,_tmpImagesCollection);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
            _statement.release();
          }
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshiplogitImagesAscomRanesvisionAppDataLocalEntityLogItImageEntity(
      @NonNull final LongSparseArray<ArrayList<LogItImageEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshiplogitImagesAscomRanesvisionAppDataLocalEntityLogItImageEntity(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`entryId`,`imagePath`,`comment`,`sortOrder` FROM `logit_images` WHERE `entryId` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "entryId");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfEntryId = 1;
      final int _cursorIndexOfImagePath = 2;
      final int _cursorIndexOfComment = 3;
      final int _cursorIndexOfSortOrder = 4;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<LogItImageEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final LogItImageEntity _item_1;
          final long _tmpId;
          _tmpId = _cursor.getLong(_cursorIndexOfId);
          final long _tmpEntryId;
          _tmpEntryId = _cursor.getLong(_cursorIndexOfEntryId);
          final String _tmpImagePath;
          _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
          final String _tmpComment;
          _tmpComment = _cursor.getString(_cursorIndexOfComment);
          final int _tmpSortOrder;
          _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
          _item_1 = new LogItImageEntity(_tmpId,_tmpEntryId,_tmpImagePath,_tmpComment,_tmpSortOrder);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
