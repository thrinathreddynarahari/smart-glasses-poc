package com.ranesvision.app.ui.dashboard;

import com.ranesvision.app.domain.usecase.ConnectDeviceUseCase;
import com.ranesvision.app.domain.usecase.ScanDevicesUseCase;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<ScanDevicesUseCase> scanDevicesUseCaseProvider;

  private final Provider<ConnectDeviceUseCase> connectDeviceUseCaseProvider;

  public DashboardViewModel_Factory(Provider<ScanDevicesUseCase> scanDevicesUseCaseProvider,
      Provider<ConnectDeviceUseCase> connectDeviceUseCaseProvider) {
    this.scanDevicesUseCaseProvider = scanDevicesUseCaseProvider;
    this.connectDeviceUseCaseProvider = connectDeviceUseCaseProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(scanDevicesUseCaseProvider.get(), connectDeviceUseCaseProvider.get());
  }

  public static DashboardViewModel_Factory create(
      Provider<ScanDevicesUseCase> scanDevicesUseCaseProvider,
      Provider<ConnectDeviceUseCase> connectDeviceUseCaseProvider) {
    return new DashboardViewModel_Factory(scanDevicesUseCaseProvider, connectDeviceUseCaseProvider);
  }

  public static DashboardViewModel newInstance(ScanDevicesUseCase scanDevicesUseCase,
      ConnectDeviceUseCase connectDeviceUseCase) {
    return new DashboardViewModel(scanDevicesUseCase, connectDeviceUseCase);
  }
}
