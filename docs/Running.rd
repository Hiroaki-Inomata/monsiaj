=begin
= �¹���ˡ

== Java2 SE 1.4

 $ java \
         -jar dist/pandaclient.jar \
         -host=localhost \
         -user=sample \
         -pass=sample \
         panda:helloworld

== Java2 SE 1.3

 $ XMLLIBS=/usr/share/java/xercesImpl.jar:/usr/share/java/xmlParserAPIs.jar
 $ java \
         -cp dist/pandaclient.jar:$XMLLIBS \
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
: monsia.document.handler
  ��������ե�����Υѡ����˻��Ѥ��륯�饹����ꡣ
  : org.montsuqi.monsia.Glade1Handler
    Glade version 1����������ե��������ѡ�
  : org.montsuqi.monsia.MonsiaHandler
    Monsia����������ե��������ѡ�
  ������ϰʲ��ΤȤ��ꡣ
  (1) monsia.document.handler����
  (2) monsia.document.handler��̤����ʤ顢����ե��������Ƭ40�Х������٤����ɤߤ��ơ�GTK-Interface�Ȥ��������õ����
  (3) GTK-Interface�λ��礬�ʤ���п�������
  (4) �����ޤǤǷ���Ǥ��ʤ���е������
: swing.defaultlaf
  ��å�&�ե��������ꡣ
  : com.sun.java.swing.plaf.windows.WindowsLookAndFeel
    Windows��(Java2 SDK 1.4�Ǥ�XP��)
  : com.sun.java.swing.plaf.motif.MotifLookAndFeel
    Motif��
  �ʤɤ������ǽ��
=end
