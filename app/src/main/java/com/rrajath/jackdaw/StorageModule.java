package com.rrajath.jackdaw;

import android.content.Context;

import com.rrajath.jackdaw.data.model.NoteMigration;
import com.rrajath.jackdaw.service.RealmService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class StorageModule {

    @Provides
    @Singleton
    RealmConfiguration provideRealmConfiguration(Context context) {
        return new RealmConfiguration.Builder(context)
                .schemaVersion(2)
                .deleteRealmIfMigrationNeeded()
                .migration(new NoteMigration())
                .build();
    }

    @Provides
    @Singleton
    Realm provideRealm(RealmConfiguration realmConfiguration) {
        Realm.setDefaultConfiguration(realmConfiguration);
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    RealmService provideRealmService(Realm realm) {
        return new RealmService(realm);
    }
}
