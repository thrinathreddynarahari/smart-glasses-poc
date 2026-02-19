package com.ranesvision.app.data.sdk;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DeviceScanner_Factory implements Factory<DeviceScanner> {
  private final Provider<Context> contextProvider;

  public DeviceScanner_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public DeviceScanner get() {
    return newInstance(contextProvider.get());
  }

  public static DeviceScanner_Factory create(Provider<Context> contextProvider) {
    return new DeviceScanner_Factory(contextProvider);
  }

  public static DeviceScanner newInstance(Context context) {
    return new DeviceScanner(context);
  }
}
