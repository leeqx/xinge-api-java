package com.tencent.xinge.bean;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

import com.tencent.xinge.bean.MessageAndroid;
import com.tencent.xinge.bean.TimeInterval;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MessageTest extends TestCase {

  @Test
  public void testAcceptTimeToJson() {
    MessageAndroid m = new MessageAndroid();
    TimeInterval acceptTime1 = new TimeInterval(1, 2, 3, 4);
    TimeInterval acceptTime2 = new TimeInterval(5, 6, 7, 8);
    m.addAcceptTime(acceptTime1);
    m.addAcceptTime(acceptTime2);
    String except =
        "[{\"start\":{\"min\":\"2\",\"hour\":\"1\"},\"end\":{\"min\":\"4\",\"hour\":\"3\"}},{\"start\":{\"min\":\"6\",\"hour\":\"5\"},\"end\":{\"min\":\"8\",\"hour\":\"7\"}}]";
    assertEquals(except, m.acceptTimeToJson());
  }

  @Test
  public void testIsValid() {
    MessageAndroid m = new MessageAndroid();
    assertEquals(0, m.getType());
    assertFalse(m.isValid());
    m.setType(MessageAndroid.TYPE_MESSAGE);
    assertTrue(m.isValid());
    m.setSendTime("abc");
    assertFalse(m.isValid());
    m.setSendTime("2013-12-25 11:49:00");
    assertTrue(m.isValid());
  }

  @Test
  public void testToJson() {
    MessageAndroid m = new MessageAndroid();
    m.setType(MessageAndroid.TYPE_MESSAGE);
    assertEquals("{\"title\":\"\",\"custom_content\":{},\"accept_time\":[],\"content\":\"\"}",
        m.toString());
  }
  
  /**
   * 不能使用 JSONObject#put(String,Map)
   * 而是使用 JSONObject#put(String, new JSONObject(Map))
   */
  @Test
  public void testJSonMap() {
	  Map<String,String> map = new HashMap<String,String>();
	  map.put("a", "a1");
	  map.put("b", "b1");
	  map.put("c", "c1");
	  JSONObject obj = new JSONObject();
	  assertEquals(obj.put("test", map), obj.put("test", new JSONObject(map)));
  }

}
