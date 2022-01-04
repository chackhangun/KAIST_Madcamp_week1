package com.camp.project1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.message.template.ButtonObject;
import com.kakao.message.template.ContentObject;
import com.kakao.message.template.FeedTemplate;
import com.kakao.message.template.LinkObject;
import com.kakao.message.template.SocialObject;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.util.helper.log.Logger;

import java.util.HashMap;
import java.util.Map;

public class MbtiResult extends Fragment implements View.OnClickListener {
    com.camp.project1.MainActivity mainActivity;
    Button sharing;
    ImageView resultimage;
    String mbti;
    String imageurl = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.mbti_result, container, false);
        resultimage = rootview.findViewById(R.id.resultView);
        sharing = rootview.findViewById(R.id.kakao_shared);
        sharing.setOnClickListener(this);
        imageurl = null;
        mbti = mainActivity.mymbti.getMBTItype();
        System.out.println("The result is = "+mbti);
        setimage(mbti);
        mainActivity.mymbti.mbti_page = 13;

        return rootview;
    }

    @Override
    public void onClick(View view) {
        kakaolink();
    }

    public void kakaolink() {
        //
        FeedTemplate params = FeedTemplate.newBuilder(ContentObject.newBuilder("MBTI 테스트",
                        imageurl,
                        LinkObject.newBuilder().setWebUrl("https://developers.kakao.com")
                                .setMobileWebUrl("https://developers.kakao.com").build())
                        .setDescrption("나의 MBTI는??")
                        .build()).setSocial(SocialObject.newBuilder().setLikeCount(10).setCommentCount(20)
                        .setSharedCount(30).setViewCount(40).build()).build();

        Map<String, String> serverCallbackArgs = new HashMap<String, String>();
        serverCallbackArgs.put("user_id", "${current_user_id}");
        serverCallbackArgs.put("product_id", "${shared_product_id}");

        KakaoLinkService.getInstance().sendDefault(getContext(), params, serverCallbackArgs, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Logger.e(errorResult.toString());
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {
                // 템플릿 밸리데이션과 쿼터 체크가 성공적으로 끝남. 톡에서 정상적으로 보내졌는지 보장은 할 수 없다. 전송 성공 유무는 서버콜백 기능을 이용하여야 한다.
            }
        });
        mainActivity.mymbti.mbti_page = 0;
        mainActivity.mymbti.backward = false;
    }

    public void setimage(String mbti){
        switch (mbti){
            case "ENFJ":
                resultimage.setImageResource(R.drawable.enfj);
                imageurl = "https://i.imgur.com/amTi5fM.png";
                break;
            case "ENFP":
                resultimage.setImageResource(R.drawable.enfp);
                imageurl = "https://i.imgur.com/qY4mtu8.png";
                break;
            case "ENTJ":
                resultimage.setImageResource(R.drawable.entj);
                imageurl = "https://i.imgur.com/IHp8P2x.png";
                break;
            case "ENTP":
                resultimage.setImageResource(R.drawable.entp);
                imageurl = "https://i.imgur.com/LKAfF6P.png";
                break;
            case "ESFJ":
                resultimage.setImageResource(R.drawable.esfj);
                imageurl = "https://i.imgur.com/71HrLaD.png";
                break;
            case "ESFP":
                resultimage.setImageResource(R.drawable.esfp);
                imageurl = "https://i.imgur.com/OOX7iVP.png";
                break;
            case "ESTJ":
                resultimage.setImageResource(R.drawable.estj);
                imageurl = "https://i.imgur.com/Voe6jm4.png";
                break;
            case "ESTP":
                resultimage.setImageResource(R.drawable.estp);
                imageurl = "https://i.imgur.com/Clr3qJ4.png";
                break;
            case "INFJ":
                resultimage.setImageResource(R.drawable.infj);
                imageurl = "https://i.imgur.com/dtYJlem.png";
                break;
            case "INFP":
                resultimage.setImageResource(R.drawable.infp);
                imageurl = "https://i.imgur.com/WN2b6z4.png";
                break;
            case "INTJ":
                resultimage.setImageResource(R.drawable.intj);
                imageurl = "https://i.imgur.com/0cmvSb5.png";
                break;
            case "INTP":
                resultimage.setImageResource(R.drawable.intp);
                imageurl = "https://i.imgur.com/6nP7ff8.png";
                break;
            case "ISFJ":
                resultimage.setImageResource(R.drawable.isfj);
                imageurl = "https://i.imgur.com/e0PtPQz.png";
                break;
            case "ISFP":
                resultimage.setImageResource(R.drawable.isfp);
                imageurl = "https://i.imgur.com/M6LVPpf.png";
                break;
            case "ISTJ":
                resultimage.setImageResource(R.drawable.istj);
                imageurl = "https://i.imgur.com/xH9C5sY.png";
                break;
            case "ISTP":
                resultimage.setImageResource(R.drawable.istp);
                imageurl = "https://i.imgur.com/H28Chza.png";
                break;
        }
    }
}