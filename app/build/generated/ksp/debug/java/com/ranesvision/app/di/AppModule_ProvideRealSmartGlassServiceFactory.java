package com.ranesvision.app.di;

import com.ranesvision.app.data.sdk.HeyCyanManager;
import com.ranesvision.app.domain.service.SmartGlassService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
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
public final class AppModule_ProvideRealSmartGlassServiceFactory implements Factory<SmartGlassService> {
  private final Provider<HeyCyanManager> realManagerProvider;

  public AppModule_ProvideRealSmartGlassServiceFactory(
      Provider<HeyCyanManager> realManagerProvider) {
    this.realManagerProvider = realManagerProvider;
  }

  @Override
  public SmartGlassService get() {
    return provideRealSmartGlassService(realManagerProvider.get());
  }

  public static AppModule_ProvideRealSmartGlassServiceFactory create(
      Provider<HeyCyanManager> realManagerProvider) {
    return new AppModule_ProvideRealSmartGlassServiceFactory(realManagerProvider);
  }

  public static SmartGlassService provideRealSmartGlassService(HeyCyanManager realManager) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideRealSmartGlassService(realManager));
  }
}
