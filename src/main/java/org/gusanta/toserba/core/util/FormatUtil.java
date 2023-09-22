package org.gusanta.toserba.core.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.gusanta.toserba.exception.FormatException;





/**
 * Alat bantu untuk melakukan format.
 *
 * @author Hariyogi
 * @since 2 Sep 2020
 */
public class FormatUtil {

  private FormatUtil() {
  }

  public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

  /**
   * Mengconvert string date ke bentuk localdate. Hanya menerima format
   * DATE_FORMAT untuk konsistensi.
   *
   * @param date format tanggal d-MM-yyyy
   * @return LocalDate
   * @throws FormatException jika format salah.
   */
  public static LocalDateTime convertToLocalDate(String date) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.SMART);
      return LocalDateTime.parse(date, formatter);
    } catch (Exception e) {
      throw new FormatException("Format tanggal harus d-MM-yyy HH:mm:ss. Contoh : 29-08-2002 12:10:00");
    }
  }

  /**
   * Mengconvert LocalDate ke dalam bentuk string. Menghasilkan format tanggal
   * dalam bentuk DATE_FORMAT.
   *
   * @param date LocalDate, tidak boleh null.
   * @return hasil format dalam bentuk String.
   */
  public static String convertToString(LocalDateTime date) {
    if (date != null) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
      return formatter.format(date);
    }
    return "";
  }

  /**
   * Mengconvert LocalDate ke dalam bentuk string. Menggunakan custom format.
   *
   * @param date LocalDate, tidak boleh null.
   * @return hasil format dalam bentuk String.
   */
  public static String convertToString(LocalDateTime date, String format) {
    if (date != null) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
      return formatter.format(date);
    }
    return "";
  }

  /**
   * Menambahkan waktu dari date.
   *
   * @param startTime Tanggal mulai.
   * @param duration  Durasi
   * @param unit      Durasi dalam
   * @return
   */
  public static Date plusDateTime(Date startTime, long duration, ChronoUnit unit) {
    Instant start = startTime.toInstant();
    Instant result = start.plus(duration, unit);
    return Date.from(result);
  }

  /**
   * Mengurangi waktu dari date.
   *
   * @param startTime Tanggal mulai.
   * @param duration  Durasi
   * @param unit      Durasi dalam
   * @return
   */
  public static Date minusDateTime(Date startTime, long duration, ChronoUnit unit) {
    Instant start = startTime.toInstant();
    Instant result = start.minus(duration, unit);
    return Date.from(result);
  }
}
