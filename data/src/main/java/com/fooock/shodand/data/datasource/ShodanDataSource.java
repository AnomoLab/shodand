package com.fooock.shodand.data.datasource;

import com.fooock.shodand.domain.model.Protocol;
import com.fooock.shodand.domain.model.TagCount;

import java.util.List;

import io.reactivex.Observable;

/**
 *
 */
public interface ShodanDataSource {

    void saveTags(List<TagCount> tags);

    Observable<List<TagCount>> getTags();

    void deleteAllTags();

    Observable<List<Protocol>> getProtocols();

    void saveProtocols(List<Protocol> protocols);

    void deleteAllProtocols();
}
