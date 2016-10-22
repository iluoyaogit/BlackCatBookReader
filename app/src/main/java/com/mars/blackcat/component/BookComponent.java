package com.mars.blackcat.component;

import com.mars.blackcat.ui.activity.BookDetailActivity;
import com.mars.blackcat.ui.activity.BooksByTagActivity;
import com.mars.blackcat.ui.activity.ReadActivity;
import com.mars.blackcat.ui.activity.SearchActivity;
import com.mars.blackcat.ui.activity.SearchByAuthorActivity;
import com.mars.blackcat.ui.fragment.BookDetailDiscussionFragment;
import com.mars.blackcat.ui.fragment.BookDetailReviewFragment;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface BookComponent {
    BookDetailActivity inject(BookDetailActivity activity);

    ReadActivity inject(ReadActivity activity);

    BooksByTagActivity inject(BooksByTagActivity activity);

    SearchActivity inject(SearchActivity activity);

    SearchByAuthorActivity inject(SearchByAuthorActivity activity);

    BookDetailReviewFragment inject(BookDetailReviewFragment fragment);

    BookDetailDiscussionFragment inject(BookDetailDiscussionFragment fragment);
}