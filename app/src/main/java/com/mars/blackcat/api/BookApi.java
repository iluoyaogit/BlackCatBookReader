package com.mars.blackcat.api;

import com.mars.blackcat.base.Constant;
import com.mars.blackcat.bean.AutoComplete;
import com.mars.blackcat.bean.BookDetail;
import com.mars.blackcat.bean.BookHelp;
import com.mars.blackcat.bean.BookHelpList;
import com.mars.blackcat.bean.BookListDetail;
import com.mars.blackcat.bean.BookListTags;
import com.mars.blackcat.bean.BookLists;
import com.mars.blackcat.bean.BookReview;
import com.mars.blackcat.bean.BookReviewList;
import com.mars.blackcat.bean.BookSource;
import com.mars.blackcat.bean.BookToc;
import com.mars.blackcat.bean.BooksByCats;
import com.mars.blackcat.bean.BooksByTag;
import com.mars.blackcat.bean.CategoryList;
import com.mars.blackcat.bean.CategoryListLv2;
import com.mars.blackcat.bean.ChapterRead;
import com.mars.blackcat.bean.CommentList;
import com.mars.blackcat.bean.DiscussionList;
import com.mars.blackcat.bean.Disscussion;
import com.mars.blackcat.bean.HotReview;
import com.mars.blackcat.bean.HotWord;
import com.mars.blackcat.bean.RankingList;
import com.mars.blackcat.bean.Rankings;
import com.mars.blackcat.bean.Recommend;
import com.mars.blackcat.bean.RecommendBookList;
import com.mars.blackcat.bean.SearchDetail;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;
import rx.Observable;

public class BookApi {

    public static BookApi instance;

    private BookApiService service;

    public BookApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(BookApiService.class);
    }

    public static BookApi getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new BookApi(okHttpClient);
        return instance;
    }

    public Observable<Recommend> getRecommend(String gender) {
        return service.getRecomend(gender);
    }

    public Observable<HotWord> getHotWord() {
        return service.getHotWord();
    }

    public Observable<AutoComplete> getAutoComplete(String query) {
        return service.autoComplete(query);
    }

    public Observable<SearchDetail> getSearchResult(String query) {
        return service.searchBooks(query);
    }

    public Observable<BooksByTag> searchBooksByAuthor(String author){
        return service.searchBooksByAuthor(author);
    }

    public Observable<BookDetail> getBookDetail(String bookId) {
        return service.getBookDetail(bookId);
    }

    public Observable<HotReview> getHotReview(String book) {
        return service.getHotReview(book);
    }

    public Observable<RecommendBookList> getRecommendBookList(String bookId, String limit) {
        return service.getRecommendBookList(bookId, limit);
    }

    public Observable<BooksByTag> getBooksByTag(String tags, String start, String limit) {
        return service.getBooksByTag(tags, start, limit);
    }

    public Observable<BookToc> getBookToc(String bookId, String view) {
        return service.getBookToc(bookId, view);
    }

    public synchronized Observable<ChapterRead> getChapterRead(String url) {
        return service.getChapterRead(url);
    }

    public synchronized Observable<List<BookSource>> getBookSource(String view, String book) {
        return service.getBookSource(view, book);
    }

    public Observable<RankingList> getRanking() {
        return service.getRanking();
    }

    public Observable<Rankings> getRanking(String rankingId) {
        return service.getRanking(rankingId);
    }

    public Observable<BookLists> getBookLists(String duration, String sort, String start, String limit, String tag, String gender) {
        return service.getBookLists(duration, sort, start, limit, tag, gender);
    }

    public Observable<BookListTags> getBookListTags() {
        return service.getBookListTags();
    }

    public Observable<BookListDetail> getBookListDetail(String bookListId) {
        return service.getBookListDetail(bookListId);
    }

    public synchronized Observable<CategoryList> getCategoryList() {
        return service.getCategoryList();
    }

    public Observable<CategoryListLv2> getCategoryListLv2() {
        return service.getCategoryListLv2();
    }

    public Observable<BooksByCats> getBooksByCats(String gender, String type, String major, String minor, int start, @Query("limit") int limit) {
        return service.getBooksByCats(gender, type, major, minor, start, limit);
    }

    public Observable<DiscussionList> getBookDisscussionList(String block, String duration, String sort, String type, String start, String limit, String distillate) {
        return service.getBookDisscussionList(block, duration, sort, type, start, limit, distillate);
    }

    public Observable<Disscussion> getBookDisscussionDetail(String disscussionId) {
        return service.getBookDisscussionDetail(disscussionId);
    }

    public Observable<CommentList> getBestComments(String disscussionId) {
        return service.getBestComments(disscussionId);
    }

    public Observable<CommentList> getBookDisscussionComments(String disscussionId, String start, String limit) {
        return service.getBookDisscussionComments(disscussionId, start, limit);
    }

    public Observable<BookReviewList> getBookReviewList(String duration, String sort, String type, String start, String limit, String distillate) {
        return service.getBookReviewList(duration, sort, type, start, limit, distillate);
    }

    public Observable<BookReview> getBookReviewDetail(String bookReviewId) {
        return service.getBookReviewDetail(bookReviewId);
    }

    public Observable<CommentList> getBookReviewComments(String bookReviewId, String start, String limit) {
        return service.getBookReviewComments(bookReviewId, start, limit);
    }

    public Observable<BookHelpList> getBookHelpList(String duration, String sort, String start, String limit, String distillate) {
        return service.getBookHelpList(duration, sort, start, limit, distillate);
    }

    public Observable<BookHelp> getBookHelpDetail(String helpId) {
        return service.getBookHelpDetail(helpId);
    }

    public Observable<DiscussionList> getBookDetailDisscussionList(String book, String sort, String type, String start, String limit) {
        return service.getBookDetailDisscussionList(book, sort, type, start, limit);
    }

    public Observable<HotReview> getBookDetailReviewList(String book, String sort,  String start, String limit) {
        return service.getBookDetailReviewList(book, sort,  start, limit);
    }

    public Observable<DiscussionList> getGirlBookDisscussionList(String block, String duration, String sort, String type, String start, String limit, String distillate) {
        return service.getBookDisscussionList(block, duration, sort, type, start, limit, distillate);
    }

}
