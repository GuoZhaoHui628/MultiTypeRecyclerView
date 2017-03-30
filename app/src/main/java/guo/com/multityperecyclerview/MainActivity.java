package guo.com.multityperecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ItemMessageBean> messageItemList;
    private RecyclerView recyMessageSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    /**
     * 获取消息数据源
     */
    private void requestMessageData(){
        messageItemList = new ArrayList<>();
        ItemMessageBean bean1 = new ItemMessageBean();
        bean1.setTitle("今晚WE打IG");
        bean1.setTime("2017-03-30");
        bean1.setContent("今晚we打ig看不看，看不看点点滴滴地对地导弹，不看算了，草莓一样送了易学，dddd，啊什么we解散了");
        bean1.setImgUrl("http://img5.dwstatic.com/lol/1601/315942968863/1451987776988.png");
        ItemMessageBean bean2 = new ItemMessageBean();
        bean2.setTitle("今晚打老虎");
        bean2.setTime("2017-03-30");
        bean2.setImgUrl("https://poyeedotme.files.wordpress.com/2012/07/20120727tiger.jpg?w=500&h=375");
        ItemMessageBean bean3 = new ItemMessageBean();
        bean3.setTitle("今晚也不知道干什么");
        bean3.setTime("2017-03-30");
        bean3.setContent("多了几分武功额文件给我切我额过后id为给外婆了各位好的few额个猥琐的几个忘了换个i我欸和工委和你宋伟额维护功能死");
        messageItemList.add(bean1);
        messageItemList.add(bean2);
        messageItemList.add(bean3);
    }

    private void initView() {
        requestMessageData();
        recyMessageSystem = (RecyclerView) this.findViewById(R.id.recyMessageSystem);
        recyMessageSystem.setLayoutManager(new LinearLayoutManager(this));
        MessageAdapter adapter = new MessageAdapter(messageItemList, this);
        recyMessageSystem.setAdapter(adapter);
    }
}
