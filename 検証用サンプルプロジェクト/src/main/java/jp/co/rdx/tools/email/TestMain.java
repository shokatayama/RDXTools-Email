package jp.co.rdx.tools.email;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
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
    // 指定パスのプロパティをロードする
    Configuration conf = loadProperties("test.properties");

    // プロパティが取得できなかった場合
    if (conf == null) {
      throw new RuntimeException("プロパティのロードに失敗したため、異常終了します。");
    }

    // [テスト]ロード内容表示
    for (Iterator<String> ite = conf.getKeys(); ite.hasNext();) {
      String key = ite.next();
      System.out.println(key + "=" + conf.getString(key));
    }

    // [テスト]メール関連の設定が送信に必要なためコメントアウト。
    // sendMail();
  }

  /**
   * プロパティロード。<br>
   * 指定したパスのプロパティファイルをロードします。
   *
   * @param path プロパティのパス
   * @return ロードしたプロパティ
   *
   */
  private Configuration loadProperties(String path) {
    PropertiesConfiguration conf = new PropertiesConfiguration();

    try {
      // ファイルから読み込み
      conf.read(new BufferedReader(new FileReader("test.properties")));
    } catch (Exception e) {
      e.printStackTrace();
      conf = null;
    }

    // 読込結果を返却する
    return conf;
  }

  private void sendMail() {
    try {
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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
