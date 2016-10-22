package com.mars.blackcat.component;

import com.mars.blackcat.ui.activity.BookDiscussionDetailActivity;
import com.mars.blackcat.ui.activity.BookHelpDetailActivity;
import com.mars.blackcat.ui.activity.BookReviewDetailActivity;
import com.mars.blackcat.ui.fragment.BookDiscussionFragment;
import com.mars.blackcat.ui.fragment.BookHelpFragment;
import com.mars.blackcat.ui.fragment.BookReviewFragment;
import com.mars.blackcat.ui.fragment.GirlBookDiscussionFragment;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface CommunityComponent {

    BookDiscussionFragment inject(BookDiscussionFragment fragment);

    BookDiscussionDetailActivity inject(BookDiscussionDetailActivity activity);

    BookReviewFragment inject(BookReviewFragment fragment);

    BookReviewDetailActivity inject(BookReviewDetailActivity activity);

    BookHelpFragment inject(BookHelpFragment fragment);

    BookHelpDetailActivity inject(BookHelpDetailActivity activity);

    GirlBookDiscussionFragment inject(GirlBookDiscussionFragment fragment);
}
