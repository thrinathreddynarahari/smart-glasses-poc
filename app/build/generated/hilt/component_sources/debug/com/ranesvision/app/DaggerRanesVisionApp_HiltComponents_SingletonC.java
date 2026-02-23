package com.ranesvision.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.ranesvision.app.data.local.AppDatabase;
import com.ranesvision.app.data.local.dao.ImageDao;
import com.ranesvision.app.data.local.dao.LogItDao;
import com.ranesvision.app.data.local.dao.TagDao;
import com.ranesvision.app.data.repository.AlbumRepository;
import com.ranesvision.app.data.repository.LogItRepository;
import com.ranesvision.app.data.sdk.DeviceConnector;
import com.ranesvision.app.data.sdk.DeviceScanner;
import com.ranesvision.app.data.sdk.GlassDeviceService;
import com.ranesvision.app.data.sdk.HeyCyanManager;
import com.ranesvision.app.data.sdk.MockSmartGlassService;
import com.ranesvision.app.di.AppModule_ProvideAppDatabaseFactory;
import com.ranesvision.app.di.AppModule_ProvideImageDaoFactory;
import com.ranesvision.app.di.AppModule_ProvideLogItDaoFactory;
import com.ranesvision.app.di.AppModule_ProvideMockSmartGlassServiceFactory;
import com.ranesvision.app.di.AppModule_ProvideRealSmartGlassServiceFactory;
import com.ranesvision.app.di.AppModule_ProvideSmartGlassServiceFactory;
import com.ranesvision.app.di.AppModule_ProvideTagDaoFactory;
import com.ranesvision.app.domain.service.SmartGlassService;
import com.ranesvision.app.domain.usecase.ConnectDeviceUseCase;
import com.ranesvision.app.domain.usecase.DisconnectUseCase;
import com.ranesvision.app.domain.usecase.ScanDevicesUseCase;
import com.ranesvision.app.ui.album.AlbumViewModel;
import com.ranesvision.app.ui.album.AlbumViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ranesvision.app.ui.dashboard.DashboardViewModel;
import com.ranesvision.app.ui.dashboard.DashboardViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ranesvision.app.ui.logit.LogItViewModel;
import com.ranesvision.app.ui.logit.LogItViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ranesvision.app.ui.settings.SettingsViewModel;
import com.ranesvision.app.ui.settings.SettingsViewModel_HiltModules_KeyModule_ProvideFactory;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SetBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerRanesVisionApp_HiltComponents_SingletonC {
  private DaggerRanesVisionApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public RanesVisionApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements RanesVisionApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public RanesVisionApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements RanesVisionApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public RanesVisionApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements RanesVisionApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public RanesVisionApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements RanesVisionApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public RanesVisionApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements RanesVisionApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public RanesVisionApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements RanesVisionApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public RanesVisionApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements RanesVisionApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public RanesVisionApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends RanesVisionApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends RanesVisionApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends RanesVisionApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends RanesVisionApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return SetBuilder.<String>newSetBuilder(4).add(AlbumViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(DashboardViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(LogItViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SettingsViewModel_HiltModules_KeyModule_ProvideFactory.provide()).build();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends RanesVisionApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AlbumViewModel> albumViewModelProvider;

    private Provider<DashboardViewModel> dashboardViewModelProvider;

    private Provider<LogItViewModel> logItViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private ScanDevicesUseCase scanDevicesUseCase() {
      return new ScanDevicesUseCase(singletonCImpl.provideSmartGlassServiceProvider.get());
    }

    private ConnectDeviceUseCase connectDeviceUseCase() {
      return new ConnectDeviceUseCase(singletonCImpl.provideSmartGlassServiceProvider.get());
    }

    private DisconnectUseCase disconnectUseCase() {
      return new DisconnectUseCase(singletonCImpl.provideSmartGlassServiceProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.albumViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.dashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.logItViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
    }

    @Override
    public Map<String, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(4).put("com.ranesvision.app.ui.album.AlbumViewModel", ((Provider) albumViewModelProvider)).put("com.ranesvision.app.ui.dashboard.DashboardViewModel", ((Provider) dashboardViewModelProvider)).put("com.ranesvision.app.ui.logit.LogItViewModel", ((Provider) logItViewModelProvider)).put("com.ranesvision.app.ui.settings.SettingsViewModel", ((Provider) settingsViewModelProvider)).build();
    }

    @Override
    public Map<String, Object> getHiltViewModelAssistedMap() {
      return Collections.<String, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.ranesvision.app.ui.album.AlbumViewModel 
          return (T) new AlbumViewModel(singletonCImpl.albumRepositoryProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 1: // com.ranesvision.app.ui.dashboard.DashboardViewModel 
          return (T) new DashboardViewModel(viewModelCImpl.scanDevicesUseCase(), viewModelCImpl.connectDeviceUseCase());

          case 2: // com.ranesvision.app.ui.logit.LogItViewModel 
          return (T) new LogItViewModel(singletonCImpl.logItRepositoryProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.ranesvision.app.ui.settings.SettingsViewModel 
          return (T) new SettingsViewModel(viewModelCImpl.scanDevicesUseCase(), viewModelCImpl.disconnectUseCase());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends RanesVisionApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends RanesVisionApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends RanesVisionApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<AppDatabase> provideAppDatabaseProvider;

    private Provider<ImageDao> provideImageDaoProvider;

    private Provider<TagDao> provideTagDaoProvider;

    private Provider<AlbumRepository> albumRepositoryProvider;

    private Provider<MockSmartGlassService> mockSmartGlassServiceProvider;

    private Provider<SmartGlassService> provideMockSmartGlassServiceProvider;

    private Provider<DeviceScanner> deviceScannerProvider;

    private Provider<DeviceConnector> deviceConnectorProvider;

    private Provider<HeyCyanManager> heyCyanManagerProvider;

    private Provider<SmartGlassService> provideRealSmartGlassServiceProvider;

    private Provider<GlassDeviceService> glassDeviceServiceProvider;

    private Provider<SmartGlassService> provideSmartGlassServiceProvider;

    private Provider<LogItDao> provideLogItDaoProvider;

    private Provider<LogItRepository> logItRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideAppDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 2));
      this.provideImageDaoProvider = DoubleCheck.provider(new SwitchingProvider<ImageDao>(singletonCImpl, 1));
      this.provideTagDaoProvider = DoubleCheck.provider(new SwitchingProvider<TagDao>(singletonCImpl, 3));
      this.albumRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AlbumRepository>(singletonCImpl, 0));
      this.mockSmartGlassServiceProvider = DoubleCheck.provider(new SwitchingProvider<MockSmartGlassService>(singletonCImpl, 7));
      this.provideMockSmartGlassServiceProvider = DoubleCheck.provider(new SwitchingProvider<SmartGlassService>(singletonCImpl, 6));
      this.deviceScannerProvider = DoubleCheck.provider(new SwitchingProvider<DeviceScanner>(singletonCImpl, 10));
      this.deviceConnectorProvider = DoubleCheck.provider(new SwitchingProvider<DeviceConnector>(singletonCImpl, 11));
      this.heyCyanManagerProvider = DoubleCheck.provider(new SwitchingProvider<HeyCyanManager>(singletonCImpl, 9));
      this.provideRealSmartGlassServiceProvider = DoubleCheck.provider(new SwitchingProvider<SmartGlassService>(singletonCImpl, 8));
      this.glassDeviceServiceProvider = DoubleCheck.provider(new SwitchingProvider<GlassDeviceService>(singletonCImpl, 5));
      this.provideSmartGlassServiceProvider = DoubleCheck.provider(new SwitchingProvider<SmartGlassService>(singletonCImpl, 4));
      this.provideLogItDaoProvider = DoubleCheck.provider(new SwitchingProvider<LogItDao>(singletonCImpl, 13));
      this.logItRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<LogItRepository>(singletonCImpl, 12));
    }

    @Override
    public void injectRanesVisionApp(RanesVisionApp ranesVisionApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.ranesvision.app.data.repository.AlbumRepository 
          return (T) new AlbumRepository(singletonCImpl.provideImageDaoProvider.get(), singletonCImpl.provideTagDaoProvider.get());

          case 1: // com.ranesvision.app.data.local.dao.ImageDao 
          return (T) AppModule_ProvideImageDaoFactory.provideImageDao(singletonCImpl.provideAppDatabaseProvider.get());

          case 2: // com.ranesvision.app.data.local.AppDatabase 
          return (T) AppModule_ProvideAppDatabaseFactory.provideAppDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.ranesvision.app.data.local.dao.TagDao 
          return (T) AppModule_ProvideTagDaoFactory.provideTagDao(singletonCImpl.provideAppDatabaseProvider.get());

          case 4: // com.ranesvision.app.domain.service.SmartGlassService 
          return (T) AppModule_ProvideSmartGlassServiceFactory.provideSmartGlassService(singletonCImpl.glassDeviceServiceProvider.get());

          case 5: // com.ranesvision.app.data.sdk.GlassDeviceService 
          return (T) new GlassDeviceService(singletonCImpl.provideMockSmartGlassServiceProvider.get(), singletonCImpl.provideRealSmartGlassServiceProvider.get());

          case 6: // @javax.inject.Named("mock") com.ranesvision.app.domain.service.SmartGlassService 
          return (T) AppModule_ProvideMockSmartGlassServiceFactory.provideMockSmartGlassService(singletonCImpl.mockSmartGlassServiceProvider.get());

          case 7: // com.ranesvision.app.data.sdk.MockSmartGlassService 
          return (T) new MockSmartGlassService();

          case 8: // @javax.inject.Named("real") com.ranesvision.app.domain.service.SmartGlassService 
          return (T) AppModule_ProvideRealSmartGlassServiceFactory.provideRealSmartGlassService(singletonCImpl.heyCyanManagerProvider.get());

          case 9: // com.ranesvision.app.data.sdk.HeyCyanManager 
          return (T) new HeyCyanManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.deviceScannerProvider.get(), singletonCImpl.deviceConnectorProvider.get());

          case 10: // com.ranesvision.app.data.sdk.DeviceScanner 
          return (T) new DeviceScanner(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 11: // com.ranesvision.app.data.sdk.DeviceConnector 
          return (T) new DeviceConnector(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 12: // com.ranesvision.app.data.repository.LogItRepository 
          return (T) new LogItRepository(singletonCImpl.provideLogItDaoProvider.get());

          case 13: // com.ranesvision.app.data.local.dao.LogItDao 
          return (T) AppModule_ProvideLogItDaoFactory.provideLogItDao(singletonCImpl.provideAppDatabaseProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
