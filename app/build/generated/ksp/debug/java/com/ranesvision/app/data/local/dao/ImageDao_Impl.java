package com.ranesvision.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.ranesvision.app.data.local.entity.ImageEntity;
import com.ranesvision.app.data.local.entity.ImageTagCrossRef;
import com.ranesvision.app.data.local.entity.ImageWithTags;
import com.ranesvision.app.data.local.entity.TagEntity;
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
public final class ImageDao_Impl implements ImageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ImageEntity> __insertionAdapterOfImageEntity;

  private final EntityInsertionAdapter<ImageTagCrossRef> __insertionAdapterOfImageTagCrossRef;

  private final SharedSQLiteStatement __preparedStmtOfRemoveImageTagCrossRef;

  private final SharedSQLiteStatement __preparedStmtOfDeleteImage;

  public ImageDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfImageEntity = new EntityInsertionAdapter<ImageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `images` (`id`,`filePath`,`thumbnailPath`,`timestamp`,`width`,`height`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ImageEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getFilePath());
        if (entity.getThumbnailPath() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getThumbnailPath());
        }
        statement.bindLong(4, entity.getTimestamp());
        statement.bindLong(5, entity.getWidth());
        statement.bindLong(6, entity.getHeight());
      }
    };
    this.__insertionAdapterOfImageTagCrossRef = new EntityInsertionAdapter<ImageTagCrossRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `image_tag_cross_ref` (`imageId`,`tagId`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ImageTagCrossRef entity) {
        statement.bindLong(1, entity.getImageId());
        statement.bindLong(2, entity.getTagId());
      }
    };
    this.__preparedStmtOfRemoveImageTagCrossRef = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM image_tag_cross_ref WHERE imageId = ? AND tagId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteImage = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM images WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertImage(final ImageEntity image, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfImageEntity.insertAndReturnId(image);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertImageTagCrossRef(final ImageTagCrossRef crossRef,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfImageTagCrossRef.insert(crossRef);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object removeImageTagCrossRef(final long imageId, final long tagId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveImageTagCrossRef.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, imageId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, tagId);
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
          __preparedStmtOfRemoveImageTagCrossRef.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteImage(final long imageId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteImage.acquire();
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
          __preparedStmtOfDeleteImage.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ImageEntity>> getAllImages() {
    final String _sql = "SELECT * FROM images ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"images"}, new Callable<List<ImageEntity>>() {
      @Override
      @NonNull
      public List<ImageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
          final int _cursorIndexOfThumbnailPath = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailPath");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfWidth = CursorUtil.getColumnIndexOrThrow(_cursor, "width");
          final int _cursorIndexOfHeight = CursorUtil.getColumnIndexOrThrow(_cursor, "height");
          final List<ImageEntity> _result = new ArrayList<ImageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ImageEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFilePath;
            _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
            final String _tmpThumbnailPath;
            if (_cursor.isNull(_cursorIndexOfThumbnailPath)) {
              _tmpThumbnailPath = null;
            } else {
              _tmpThumbnailPath = _cursor.getString(_cursorIndexOfThumbnailPath);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final int _tmpWidth;
            _tmpWidth = _cursor.getInt(_cursorIndexOfWidth);
            final int _tmpHeight;
            _tmpHeight = _cursor.getInt(_cursorIndexOfHeight);
            _item = new ImageEntity(_tmpId,_tmpFilePath,_tmpThumbnailPath,_tmpTimestamp,_tmpWidth,_tmpHeight);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ImageWithTags>> getAllImagesWithTags() {
    final String _sql = "SELECT * FROM images ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"image_tag_cross_ref", "tags",
        "images"}, new Callable<List<ImageWithTags>>() {
      @Override
      @NonNull
      public List<ImageWithTags> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
            final int _cursorIndexOfThumbnailPath = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailPath");
            final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
            final int _cursorIndexOfWidth = CursorUtil.getColumnIndexOrThrow(_cursor, "width");
            final int _cursorIndexOfHeight = CursorUtil.getColumnIndexOrThrow(_cursor, "height");
            final LongSparseArray<ArrayList<TagEntity>> _collectionTags = new LongSparseArray<ArrayList<TagEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionTags.containsKey(_tmpKey)) {
                _collectionTags.put(_tmpKey, new ArrayList<TagEntity>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshiptagsAscomRanesvisionAppDataLocalEntityTagEntity(_collectionTags);
            final List<ImageWithTags> _result = new ArrayList<ImageWithTags>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final ImageWithTags _item;
              final ImageEntity _tmpImage;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final String _tmpFilePath;
              _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
              final String _tmpThumbnailPath;
              if (_cursor.isNull(_cursorIndexOfThumbnailPath)) {
                _tmpThumbnailPath = null;
              } else {
                _tmpThumbnailPath = _cursor.getString(_cursorIndexOfThumbnailPath);
              }
              final long _tmpTimestamp;
              _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
              final int _tmpWidth;
              _tmpWidth = _cursor.getInt(_cursorIndexOfWidth);
              final int _tmpHeight;
              _tmpHeight = _cursor.getInt(_cursorIndexOfHeight);
              _tmpImage = new ImageEntity(_tmpId,_tmpFilePath,_tmpThumbnailPath,_tmpTimestamp,_tmpWidth,_tmpHeight);
              final ArrayList<TagEntity> _tmpTagsCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpTagsCollection = _collectionTags.get(_tmpKey_1);
              _item = new ImageWithTags(_tmpImage,_tmpTagsCollection);
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
  public Flow<List<ImageWithTags>> searchImagesByTag(final String query) {
    final String _sql = "\n"
            + "        SELECT DISTINCT images.* FROM images\n"
            + "        INNER JOIN image_tag_cross_ref ON images.id = image_tag_cross_ref.imageId\n"
            + "        INNER JOIN tags ON image_tag_cross_ref.tagId = tags.id\n"
            + "        WHERE LOWER(tags.name) LIKE '%' || LOWER(?) || '%'\n"
            + "        ORDER BY images.timestamp DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"image_tag_cross_ref", "tags",
        "images"}, new Callable<List<ImageWithTags>>() {
      @Override
      @NonNull
      public List<ImageWithTags> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
            final int _cursorIndexOfThumbnailPath = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailPath");
            final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
            final int _cursorIndexOfWidth = CursorUtil.getColumnIndexOrThrow(_cursor, "width");
            final int _cursorIndexOfHeight = CursorUtil.getColumnIndexOrThrow(_cursor, "height");
            final LongSparseArray<ArrayList<TagEntity>> _collectionTags = new LongSparseArray<ArrayList<TagEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionTags.containsKey(_tmpKey)) {
                _collectionTags.put(_tmpKey, new ArrayList<TagEntity>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshiptagsAscomRanesvisionAppDataLocalEntityTagEntity(_collectionTags);
            final List<ImageWithTags> _result = new ArrayList<ImageWithTags>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final ImageWithTags _item;
              final ImageEntity _tmpImage;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final String _tmpFilePath;
              _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
              final String _tmpThumbnailPath;
              if (_cursor.isNull(_cursorIndexOfThumbnailPath)) {
                _tmpThumbnailPath = null;
              } else {
                _tmpThumbnailPath = _cursor.getString(_cursorIndexOfThumbnailPath);
              }
              final long _tmpTimestamp;
              _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
              final int _tmpWidth;
              _tmpWidth = _cursor.getInt(_cursorIndexOfWidth);
              final int _tmpHeight;
              _tmpHeight = _cursor.getInt(_cursorIndexOfHeight);
              _tmpImage = new ImageEntity(_tmpId,_tmpFilePath,_tmpThumbnailPath,_tmpTimestamp,_tmpWidth,_tmpHeight);
              final ArrayList<TagEntity> _tmpTagsCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpTagsCollection = _collectionTags.get(_tmpKey_1);
              _item = new ImageWithTags(_tmpImage,_tmpTagsCollection);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshiptagsAscomRanesvisionAppDataLocalEntityTagEntity(
      @NonNull final LongSparseArray<ArrayList<TagEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshiptagsAscomRanesvisionAppDataLocalEntityTagEntity(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `tags`.`id` AS `id`,`tags`.`name` AS `name`,_junction.`imageId` FROM `image_tag_cross_ref` AS _junction INNER JOIN `tags` ON (_junction.`tagId` = `tags`.`id`) WHERE _junction.`imageId` IN (");
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
      // _junction.imageId;
      final int _itemKeyIndex = 2;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfName = 1;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<TagEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final TagEntity _item_1;
          final long _tmpId;
          _tmpId = _cursor.getLong(_cursorIndexOfId);
          final String _tmpName;
          _tmpName = _cursor.getString(_cursorIndexOfName);
          _item_1 = new TagEntity(_tmpId,_tmpName);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
