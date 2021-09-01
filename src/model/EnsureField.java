package model;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Utility class to ensure fields are within range of their expected values.
 */
public class EnsureField {

  /**
   * Ensures the given predicate is true with the given value. Otherwise, throw an
   * IllegalArgumentException with the given error message.
   *
   * @param pred  the predicate to test the value on
   * @param value the value to test
   * @param error the error message to throw
   * @param <T>   The generic type for the Predicate and the value to test
   * @throws IllegalArgumentException if testing the value with the given predicate returns true
   */
  public static <T> void ensure(Predicate<T> pred, T value, String error)
      throws IllegalArgumentException {
    if (pred.test(value)) {
      throw new IllegalArgumentException(error);
    }
  }


  /**
   * If the given value is not null, do nothing. Otherwise, throw an IllegalArgumentException with
   * the given error.
   *
   * @param value the value to check
   * @param error the error message to throw if the given value is null
   * @param <T>   value is of generic type T
   * @throws IllegalArgumentException if the given value is null
   */
  public static <T> void ensureNotNull(T value, String error) throws IllegalArgumentException {
    ensure(Objects::isNull, value, error);
  }

  /**
   * If the given value is greater than the lower bound, do nothing. Otherwise, throw an
   * IllegalArgumentException with the given error.
   *
   * @param value      the value to check
   * @param lowerBound the bound to check if the given value is in range
   * @param error      the error message to throw if the given value is out of bounds
   * @throws IllegalArgumentException if the value is less than or equal to the lower bound
   */
  public static void ensureGreater(double value, double lowerBound, String error)
      throws IllegalArgumentException {
    ensure((x) -> x <= lowerBound, value, error);
  }


  /**
   * If the given value is less than the lower bound, do nothing. Otherwise, throw an
   * IllegalArgumentException with the given error.
   *
   * @param value      the value to check
   * @param upperBound the bound to check if the given value is in range
   * @param error      the error message to throw if the given value is out of bounds
   * @throws IllegalArgumentException if the value is greater than or equal to the upper bound
   */
  public static void ensureLess(double value, double upperBound, String error)
      throws IllegalArgumentException {
    ensure((x) -> x >= upperBound, value, error);
  }

  /**
   * Checks the sign of the value. If requirePositive is true, check if the value is positive.
   * Otherwise, check if the value is negative. Zero passes both tests for either positive or
   * negative.
   *
   * @param value           the value to check
   * @param requirePositive if the sign should be positive or negative
   * @param error           the error message to throw
   * @throws IllegalArgumentException if the value is not the required sign
   */
  public static void ensureSign(double value, boolean requirePositive, String error)
      throws IllegalArgumentException {
    if (requirePositive) {
      ensure((x) -> x < 0, value, error);
    } else {
      ensure((x) -> x > 0, value, error);
    }
  }
}
