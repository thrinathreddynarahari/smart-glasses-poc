package com.ranesvision.app.ui.settings;

import com.ranesvision.app.domain.usecase.DisconnectUseCase;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<ScanDevicesUseCase> scanDevicesUseCaseProvider;

  private final Provider<DisconnectUseCase> disconnectUseCaseProvider;

  public SettingsViewModel_Factory(Provider<ScanDevicesUseCase> scanDevicesUseCaseProvider,
      Provider<DisconnectUseCase> disconnectUseCaseProvider) {
    this.scanDevicesUseCaseProvider = scanDevicesUseCaseProvider;
    this.disconnectUseCaseProvider = disconnectUseCaseProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(scanDevicesUseCaseProvider.get(), disconnectUseCaseProvider.get());
  }

  public static SettingsViewModel_Factory create(
      Provider<ScanDevicesUseCase> scanDevicesUseCaseProvider,
      Provider<DisconnectUseCase> disconnectUseCaseProvider) {
    return new SettingsViewModel_Factory(scanDevicesUseCaseProvider, disconnectUseCaseProvider);
  }

  public static SettingsViewModel newInstance(ScanDevicesUseCase scanDevicesUseCase,
      DisconnectUseCase disconnectUseCase) {
    return new SettingsViewModel(scanDevicesUseCase, disconnectUseCase);
  }
}
