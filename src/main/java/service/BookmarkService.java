package service;

import dao.BookmarkDao;
import model.BookMark;
import util.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookmarkService {

    /**
     * 북마크 리스트 가져오기
     *
     * @return 북마크 리스트
     */
    public List<BookMark> getBookmarkList() {
        List<BookMark> bookMarkList;

        try (Connection conn = DbUtil.getConnection()) {
            BookmarkDao bookmarkDao = new BookmarkDao();
            bookMarkList = bookmarkDao.getBookmarkList(conn);
        } catch (SQLException e) {
            throw new RuntimeException("북마크 리스트를 가져오는 중 오류가 발생했습니다.", e);
        }
        return bookMarkList;
    }

    /**
     * 북마크 상세 정보
     *
     * @param bookmarkId 북마크 아이디
     * @return 북마크 상세 정보
     */
    public BookMark getBookmarkDetail(int bookmarkId) {
        BookMark bookMark;

        try (Connection conn = DbUtil.getConnection()) {
            BookmarkDao bookmarkDao = new BookmarkDao();
            bookMark = bookmarkDao.getBookmarkDetail(conn, bookmarkId);
        } catch (SQLException e) {
            throw new RuntimeException("북마크 상세정보를 가져오는 중 오류가 발생했습니다.", e);
        }
        return bookMark;

    }

    /**
     * 북마크 생성
     *
     * @param wifiMgrNo       와이파이 관리 번호
     * @param bookmarkGroupId 북마크 그룹 아이디
     */
    public void addBookmark(String wifiMgrNo, int bookmarkGroupId) {
        try (Connection conn = DbUtil.getConnection()) {
            BookmarkDao bookmarkDao = new BookmarkDao();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime now = LocalDateTime.now();
            String formattedDateTime = now.format(formatter);
            if (bookmarkDao.isDuplicateBookmark(conn, wifiMgrNo, bookmarkGroupId)) {
                throw new RuntimeException("이미 등록된 북마크 입니다.");
            } else {
                bookmarkDao.insertBookmark(conn, wifiMgrNo, bookmarkGroupId, formattedDateTime);
            }
        } catch (SQLException e) {
            throw new RuntimeException("북마크를 등록 하는 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 북마크 삭제
     *
     * @param bookmarkId 북마크 아이디
     */
    public void deleteBookmark(int bookmarkId) {
        try (Connection conn = DbUtil.getConnection()) {
            BookmarkDao bookmarkDao = new BookmarkDao();
            bookmarkDao.deleteBookmark(conn, bookmarkId);
        } catch (SQLException e) {
            throw new RuntimeException("북마크를 삭제하는 중 오류가 발생했습니다.", e);
        }
    }

}
