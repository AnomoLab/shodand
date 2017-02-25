package com.fooock.app.shodand.view;

import android.support.annotation.NonNull;

import com.fooock.shodand.domain.model.TagCount;

import java.util.List;

/**
 *
 */
public interface ExploreView extends BaseView {

    /**
     * Click listener for the {@link com.fooock.app.shodand.view.adapter.QueriesAdapter}
     */
    interface QueryListener {
        void onQueryListSelected();

        void onQuerySearchSelected();
    }

    /**
     * Click listener for the {@link com.fooock.app.shodand.view.adapter.PopularTagAdapter}
     */
    interface TagListener {
        void onTagSelected(@NonNull TagCount tag);
    }

    /**
     * Click listener for the {@link com.fooock.app.shodand.view.adapter.ServicesAdapter}
     */
    interface ServiceListener {
        void onSearchByIp();

        void onSearchSummaryInfo();

        void onSearchServices();
    }

    void showLoading();

    void hideLoading();

    void showPopularTags(List<TagCount> tags);

    void showUnexpectedError();
}
