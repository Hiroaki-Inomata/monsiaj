=begin
= �¹���ˡ
== jar���������֤���Ѥ�����

 $ java \
         -jar dist/pandaclient.jar \
         -host=localhost \
         -user=sample \
         -pass=sample \
         panda:helloworld

== without jar

 $ java \
         -cp bin:src \
         org.montsuqi.client.Client \
         -host=localhost \
         -user=sample \
         -pass=sample \
         panda:helloworld

= �ץ�ѥƥ�

: monsia.logger.factory
  ���󥰤˻��Ѥ��륯�饹����ꡣ����Ǥ���Τϰʲ���4�̤ꡣ
  : org.montsuqi.util.NullLogger
    ���ڥ�����Ϥ��ʤ���
  : org.montsuqi.util.StdErrLogger
    ɸ�२�顼���Ϥ˽��ϡ�
  : org.montsuqi.util.Log4JLogger
    Apache��Log4J����ѡ�
  : org.montsuqi.util.J2SELogger
    Java2 SDK 1.4�Υ���API����ѡ�
: swing.defaultlaf
  ��å�&�ե��������ꡣ
  : com.sun.java.swing.plaf.windows.WindowsLookAndFeel
    Windows��(Java2 SDK 1.4�Ǥ�XP��)
  : com.sun.java.swing.plaf.motif.MotifLookAndFeel
    Motif��
  �ʤɤ������ǽ��
=end
