package jp.co.rdx.tools.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/**
 * 検証用メインクラス。<br>
 * 開発メンバーの検証に利用する。
 *
 * @author n.k
 */
public class TestMain {

  /**
   * メインメソッド。<br>
   * 本クラスのメイン処理を実行します。
   *
   * @param args 実行時引数（使用しない）
   */
  public static void main(String[] args) {
    try {
      // メイン処理を実行する
      new TestMain().execute();
    }
    // 例外発生時はココでキャッチする
    catch (Exception e) {
      // 発生した例外のスタックトレースをコンソールに出力する
      e.printStackTrace();
    }
  }

  /**
   * 本クラスのメイン処理。<br>
   * 検証したい処理はココで試してね！
   *
   * @throws Exception メイン処理実行中に例外が発生した場合
   */
  private void execute() throws Exception {
    // TODO 処理をココに追加。
    Email email = new SimpleEmail();
    email.setHostName("[HostName]");
    email.setSmtpPort(465);
    email.setAuthenticator(new DefaultAuthenticator("[UserName]", "[Password]"));
    email.setSSLOnConnect(true);
    email.setFrom("FromMailAddress@xxxx.vo.jp");
    email.setSubject("test");
    email.setMsg("testmail");
    email.addTo("[ToMailAddress@xxxx.co.jp]");
    email.setDebug(true);
    email.send();
  }
}
