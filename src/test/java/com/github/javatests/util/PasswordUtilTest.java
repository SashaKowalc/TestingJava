package com.github.javatests.util;

import com.github.javatests.util.PasswordUtil.SecurityLevel;
import org.junit.Assert;
import org.junit.Test;

import static com.github.javatests.util.PasswordUtil.SecurityLevel.*;
import static org.junit.Assert.*;

public class PasswordUtilTest {

  @Test
  public void weak_when_has_less_than_8_letters() {
    assertEquals(WEAK, PasswordUtil.accessPassword("as12!"));
  }

  @Test
  public void weak_when_has_only_letters() {
    assertEquals(WEAK, PasswordUtil.accessPassword("abcdefghijklm"));
  }

  @Test
  public void medium_when_has_letters_and_numbers() {
    assertEquals(MEDIUM, PasswordUtil.accessPassword("abcdef1234567"));
  }

  @Test
  public void strong_when_has_letters_numbers_and_symbols() {
    assertEquals(STRONG, PasswordUtil.accessPassword("abcdef12345!"));
  }
}