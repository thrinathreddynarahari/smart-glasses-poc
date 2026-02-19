package com.ranesvision.app.domain.usecase;

import com.ranesvision.app.domain.service.SmartGlassService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ScanDevicesUseCase_Factory implements Factory<ScanDevicesUseCase> {
  private final Provider<SmartGlassService> serviceProvider;

  public ScanDevicesUseCase_Factory(Provider<SmartGlassService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public ScanDevicesUseCase get() {
    return newInstance(serviceProvider.get());
  }

  public static ScanDevicesUseCase_Factory create(Provider<SmartGlassService> serviceProvider) {
    return new ScanDevicesUseCase_Factory(serviceProvider);
  }

  public static ScanDevicesUseCase newInstance(SmartGlassService service) {
    return new ScanDevicesUseCase(service);
  }
}
