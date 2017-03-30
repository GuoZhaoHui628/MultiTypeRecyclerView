package guo.com.multityperecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ${GuoZhaoHui} on 2017/3/30.
 * email:guozhaohui628@gmail.com
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemMessageBean> messageBeanList;
    private Context mContext;
    private static final int MESSAGE_ADAPTER_TYPE_DOUBLE = 0000;   //图文
    private static final int MESSAGE_ADAPTER_TYPE_SINGLECONTENT = 1111;   //只有内容
    private static final int MESSAGE_ADAPTER_TYPE_IMG = 2222;   //只有图片


    public MessageAdapter(List<ItemMessageBean> messageBeanList, Context mContext) {
        this.messageBeanList = messageBeanList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==MESSAGE_ADAPTER_TYPE_DOUBLE){  //都有类型
            View viewDouble =  LayoutInflater.from(mContext).inflate(R.layout.item_message_double,parent,false);
            final ViewHolderDouble viewHolderDouble = new ViewHolderDouble(viewDouble);
            viewHolderDouble.vhd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolderDouble.getAdapterPosition();
                    Toast.makeText(mContext,"我是点击事件,位置  "+position+"  标题-->"+messageBeanList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                }
            });
            return viewHolderDouble;
        }else if(viewType==MESSAGE_ADAPTER_TYPE_SINGLECONTENT){  //只有内容
            View viewSingleContent =  LayoutInflater.from(mContext).inflate(R.layout.item_message_singlecontent,parent,false);
            final ViewHolderSingelContent viewHolderContent = new ViewHolderSingelContent(viewSingleContent);
            viewHolderContent.vhc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolderContent.getAdapterPosition();
                    Toast.makeText(mContext,"我是点击事件,位置  "+position+"  标题-->"+messageBeanList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                }
            });
            return viewHolderContent;
        }else if(viewType==MESSAGE_ADAPTER_TYPE_IMG){  //只有图片
            View viewSingleImg =  LayoutInflater.from(mContext).inflate(R.layout.item_message_singleimg,parent,false);
            final ViewHolderSingelImg viewHolderImg = new ViewHolderSingelImg(viewSingleImg);
            viewHolderImg.vhi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolderImg.getAdapterPosition();
                    Toast.makeText(mContext,"我是点击事件,位置  "+position+"  标题-->"+messageBeanList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                }
            });
            return viewHolderImg;
        }
        return null;
    }

    /**
     * 因为这个类继承的RecyclerView.Adapter<RecyclerView.ViewHolder>泛型是父类的ViewHolder，
     * 所以这里返回的holder，也是最初的，需要对我们自己定义的三个viewholder判断,判断这个holder对象是自己定义的三个类的实例,然后根据自己需要的
     * ViewHolder获取控件，绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemMessageBean bean = messageBeanList.get(position);
        if(holder instanceof ViewHolderDouble){
            ViewHolderDouble vhd = (ViewHolderDouble) holder;
            vhd.tv_item_message_title.setText(bean.getTitle());
            vhd.tv_item_message_time.setText(bean.getTime());
            vhd.tv_item_message_content.setText(bean.getContent());
            //加载图片这里我使用picasso，按道理这种列表中的图片加载使用fresco比较好
            Picasso.with(mContext).load(bean.getImgUrl()).into(vhd.iv_item_message_imglogo);
        }else if(holder instanceof ViewHolderSingelContent){
            ViewHolderSingelContent vhc = (ViewHolderSingelContent) holder;
            vhc.tv_item_message_title.setText(bean.getTitle());
            vhc.tv_item_message_time.setText(bean.getTime());
            vhc.tv_item_message_content.setText(bean.getContent());
        }else if(holder instanceof ViewHolderSingelImg){
            ViewHolderSingelImg vhi = (ViewHolderSingelImg) holder;
            vhi.tv_item_message_title.setText(bean.getTitle());
            vhi.tv_item_message_time.setText(bean.getTime());
            Picasso.with(mContext).load(bean.getImgUrl()).into(vhi.iv_item_message_imglogo);
        }
    }

    @Override
    public int getItemCount() {
        return messageBeanList.size();
    }


    /**
     * 判断集合中某一对象content，img两个是否有数据，根据不同情况返回类型识别码,用于加载不同的布局
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        ItemMessageBean bean = messageBeanList.get(position);
        if(!TextUtils.isEmpty(bean.getContent())&&!TextUtils.isEmpty(bean.getImgUrl())){  //内容和图片数据都有
            return MESSAGE_ADAPTER_TYPE_DOUBLE;
        }else if(TextUtils.isEmpty(bean.getContent())&&!TextUtils.isEmpty(bean.getImgUrl())){  //只有图片
            return MESSAGE_ADAPTER_TYPE_IMG;
        }else if(!TextUtils.isEmpty(bean.getContent())&& TextUtils.isEmpty(bean.getImgUrl())){ //只有文字
            return MESSAGE_ADAPTER_TYPE_SINGLECONTENT;
        }
        return -1;
    }

    class ViewHolderDouble extends RecyclerView.ViewHolder{
        View vhd;
        TextView tv_item_message_title;
        TextView tv_item_message_time;
        TextView tv_item_message_content;
        ImageView iv_item_message_imglogo;

        public ViewHolderDouble(View itemView) {
            super(itemView);
            vhd = itemView;
            tv_item_message_title = (TextView) itemView.findViewById(R.id.tv_item_message_title);
            tv_item_message_time = (TextView) itemView.findViewById(R.id.tv_item_message_time);
            tv_item_message_content = (TextView) itemView.findViewById(R.id.tv_item_message_content);
            iv_item_message_imglogo = (ImageView) itemView.findViewById(R.id.iv_item_message_imglogo);
        }
    }

    class ViewHolderSingelContent extends RecyclerView.ViewHolder{
        View vhc;
        TextView tv_item_message_title;
        TextView tv_item_message_time;
        TextView tv_item_message_content;


        public ViewHolderSingelContent(View itemView) {
            super(itemView);
            vhc = itemView;
            tv_item_message_title = (TextView) itemView.findViewById(R.id.tv_item_message_title);
            tv_item_message_time = (TextView) itemView.findViewById(R.id.tv_item_message_time);
            tv_item_message_content = (TextView) itemView.findViewById(R.id.tv_item_message_content);

        }
    }

    class ViewHolderSingelImg extends RecyclerView.ViewHolder{
        View vhi;
        TextView tv_item_message_title;
        TextView tv_item_message_time;
        ImageView iv_item_message_imglogo;


        public ViewHolderSingelImg(View itemView) {
            super(itemView);
            vhi = itemView;
            tv_item_message_title = (TextView) itemView.findViewById(R.id.tv_item_message_title);
            tv_item_message_time = (TextView) itemView.findViewById(R.id.tv_item_message_time);
            iv_item_message_imglogo = (ImageView) itemView.findViewById(R.id.iv_item_message_imglogo);
        }
    }
}
