import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

public class DeleteTest {
    /**
     * 删除的方式有多样：
     * 1）根据Term删除，需要注意：
     *    a. 词条的数据类型必须是字符串
     *    b. 最好根据id进行唯一匹配删除，如果id不是字符串类型怎么办？
     * 2）根据Query删除
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
        // 创建目录对象
        Directory directory = FSDirectory.open(new File("F:\\indexDir"));
        // 创建配置对象
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());
        // 创建索引写出工具
        IndexWriter writer = new IndexWriter(directory, conf);

        // 根据词条进行删除
      	//writer.deleteDocuments(new Term("id", "1"));

        // 根据query对象删除,如果ID是数值类型，那么我们可以用数值范围查询锁定一个具体的ID
        //		Query query = NumericRangeQuery.newLongRange("id", 2L, 2L, true, true);
        //		writer.deleteDocuments(query);

        // 删除所有
        writer.deleteAll();
        // 提交
        writer.commit();
        // 关闭
        writer.close();
    }
}
