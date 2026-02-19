package com.ranesvision.app.data.sdk;

import com.ranesvision.app.domain.service.SmartGlassService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class GlassDeviceService_Factory implements Factory<GlassDeviceService> {
  private final Provider<SmartGlassService> mockServiceProvider;

  private final Provider<SmartGlassService> realServiceProvider;

  public GlassDeviceService_Factory(Provider<SmartGlassService> mockServiceProvider,
      Provider<SmartGlassService> realServiceProvider) {
    this.mockServiceProvider = mockServiceProvider;
    this.realServiceProvider = realServiceProvider;
  }

  @Override
  public GlassDeviceService get() {
    return newInstance(mockServiceProvider.get(), realServiceProvider.get());
  }

  public static GlassDeviceService_Factory create(Provider<SmartGlassService> mockServiceProvider,
      Provider<SmartGlassService> realServiceProvider) {
    return new GlassDeviceService_Factory(mockServiceProvider, realServiceProvider);
  }

  public static GlassDeviceService newInstance(SmartGlassService mockService,
      SmartGlassService realService) {
    return new GlassDeviceService(mockService, realService);
  }
}
