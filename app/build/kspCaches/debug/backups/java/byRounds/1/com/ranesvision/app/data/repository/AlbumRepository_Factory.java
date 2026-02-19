package com.ranesvision.app.data.repository;

import com.ranesvision.app.data.local.dao.ImageDao;
import com.ranesvision.app.data.local.dao.TagDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AlbumRepository_Factory implements Factory<AlbumRepository> {
  private final Provider<ImageDao> imageDaoProvider;

  private final Provider<TagDao> tagDaoProvider;

  public AlbumRepository_Factory(Provider<ImageDao> imageDaoProvider,
      Provider<TagDao> tagDaoProvider) {
    this.imageDaoProvider = imageDaoProvider;
    this.tagDaoProvider = tagDaoProvider;
  }

  @Override
  public AlbumRepository get() {
    return newInstance(imageDaoProvider.get(), tagDaoProvider.get());
  }

  public static AlbumRepository_Factory create(Provider<ImageDao> imageDaoProvider,
      Provider<TagDao> tagDaoProvider) {
    return new AlbumRepository_Factory(imageDaoProvider, tagDaoProvider);
  }

  public static AlbumRepository newInstance(ImageDao imageDao, TagDao tagDao) {
    return new AlbumRepository(imageDao, tagDao);
  }
}
