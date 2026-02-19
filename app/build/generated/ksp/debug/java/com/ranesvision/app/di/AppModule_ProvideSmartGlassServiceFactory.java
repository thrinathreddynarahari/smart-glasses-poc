package com.ranesvision.app.di;

import com.ranesvision.app.data.sdk.GlassDeviceService;
import com.ranesvision.app.domain.service.SmartGlassService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideSmartGlassServiceFactory implements Factory<SmartGlassService> {
  private final Provider<GlassDeviceService> glassDeviceServiceProvider;

  public AppModule_ProvideSmartGlassServiceFactory(
      Provider<GlassDeviceService> glassDeviceServiceProvider) {
    this.glassDeviceServiceProvider = glassDeviceServiceProvider;
  }

  @Override
  public SmartGlassService get() {
    return provideSmartGlassService(glassDeviceServiceProvider.get());
  }

  public static AppModule_ProvideSmartGlassServiceFactory create(
      Provider<GlassDeviceService> glassDeviceServiceProvider) {
    return new AppModule_ProvideSmartGlassServiceFactory(glassDeviceServiceProvider);
  }

  public static SmartGlassService provideSmartGlassService(GlassDeviceService glassDeviceService) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSmartGlassService(glassDeviceService));
  }
}
