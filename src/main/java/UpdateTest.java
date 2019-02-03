import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

public class UpdateTest {
    @Test
    public void testUpdate() throws Exception{
        // 创建目录对象
        Directory directory = FSDirectory.open(new File("F:\\indexDir"));
        // 创建配置对象
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());
        // 创建索引写出工具
        IndexWriter writer = new IndexWriter(directory, conf);

        // 创建新的文档数据
        Document doc = new Document();
        doc.add(new StringField("id","1", Field.Store.YES));
        doc.add(new TextField("title","谷歌地图之父跳槽facebook 为了加入传智播客 屌爆了啊", Field.Store.YES));
        /* 修改索引。参数：
         * 	词条：根据这个词条匹配到的所有文档都会被修改
         * 	文档信息：要修改的新的文档数据
         */
        writer.updateDocument(new Term("id","1"), doc);
        // 提交
        writer.commit();
        // 关闭
        writer.close();
    }
}
