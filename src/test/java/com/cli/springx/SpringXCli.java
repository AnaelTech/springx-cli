package com.cli.springx;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SpringXCli
    extends TestCase {
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public SpringXCli(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(SpringXCli.class);
  }

  /**
   * Rigourous Test :-)
   */
  public void testApp() {
    assertTrue(true);
  }
}
