package com.github.javatests.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {


  @Test
  public void repeatStringOnce() {
    Assert.assertEquals("hola", StringUtil.repeat("hola", 1));
  }

  @Test
  public void repeatStringMultipleTimes() {
    Assert.assertEquals("holaholahola", StringUtil.repeat("hola", 3));
  }

  @Test
  public void repeatStringZeroTimes() {
    Assert.assertEquals("", StringUtil.repeat("hola", 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void repeatStringNegativeTimes() {
    Assert.assertEquals("hola", StringUtil.repeat("hola", -1));
  }

  @Test
  public void string_is_not_empty() {
    Assert.assertFalse(StringUtil.isEmpty("Sasha"));
  }

  @Test
  public void empty_quote_is_empty() {
    Assert.assertTrue(StringUtil.isEmpty(""));
  }

  @Test
  public void null_is_string_empty() {
    Assert.assertTrue(StringUtil.isEmpty(null));
  }

  @Test
  public void spaces_is_string_empty() {
    Assert.assertTrue(StringUtil.isEmpty(" "));
  }

}
