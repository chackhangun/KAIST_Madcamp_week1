# README.md

# First Tap: Contact

- Component
    - Database
        
        안드로이드의 기본 연락처서비스를 사용하지 않고 연락처를 추가, 삭제한다.
        
        ContactDBManager와 ContactProvider 클래스를 사용해서 Database를 구현함. ContactDBManager는 SQLiteOpenHelper를 상속하고, ContactProvider는 ContentProvider를 상속하는 클래스다. 
        
        ContentProvider : 안드로이드 시스템의 설정값, DB 에 접근하게 해주는 클래스
        
        SQLiteOpenHelper : DB 생성과 버전관리를 해주는 클래스
        
        ```java
        public class ContactDBManager extends SQLiteOpenHelper {
        
            static final String DB_CONTACTS = "Contacts.db";
            static final String TABLE_CONTACT = "Contacts";
            static final int DB_VERSION = 2;
            Context context = null;
            private static ContactDBManager dbManager = null;
        
            public static ContactDBManager getInstance(Context context){
                if(dbManager == null){
                    dbManager = new ContactDBManager(context, DB_CONTACTS, null, DB_VERSION);
                }
                return dbManager;
            }
        ....
        }
        ```
        
        ContactDBManager의 getInstance함수로 내부 저장소dp “Contacts.db”가 존재하지 않으면 새로 테이블을 생성한다. 존재한다면 기존의 테이블을 사용한다.
        
- Function
    - Add
        
        연락처에서 +버튼을 누르면 연락처를 저장할 수 있는 AddContentActivity로 연결된다. 해당 클래스 내부에서 EditText에 적힌 name, number를 받아와서 Contentvalue를 통해 DB에 추가해준다. 
        
        ```java
        @Override
            public void onClick(View view){
                switch(view.getId()){
                    case R.id.save:
                        String itemname = name.getText().toString();
                        String itemnumber = number.getText().toString();
        
                        ContentValues newContact = new ContentValues();
                        newContact.put("name", itemname);
                        newContact.put("phone", itemnumber);
        
                        getContentResolver().insert(Uri.parse(MainActivity.PROVIDER_URI), newContact);
                        startActivity(new Intent(this, MainActivity.class));
                        break;
        ```
        ![ezgif com-gif-maker](https://user-images.githubusercontent.com/32477937/148048583-932cc88c-997d-431d-bdb7-08706606c93f.gif) 

        
    - Recycler Adapter
        
        연락처를 클릭하게 되면 옵션이 나타나는 효과가 있다. 이 때 recyclerview 내부의 viewholder들의 position이 모두 바뀌게 된다. 클릭해서 옵션이 나타날 때와 다른 아이템을 클릭해서 옵션이 사라질때를 위해 현재 poisiton과 이전 position(preposition)을 저장해둔다. changeVisibilitiy는 옵션이 있는 경우, position에 대한 정보를 삭제시켜 모든 item들을 위로 올리는 동작을 수행하고, 다른 아이템을 클릭하는 경우는 해당 바를 없애고 클릭한 item에 해당하는 옵션바를 새로 나타나게 하는 동작을 수행한다.
        
        ```java
        case R.id.linearLayout:
                            m_number = number.getText().toString();
                            if (selectedItems.get(position)) {
                                // 펼쳐진 Item을 클릭 시
                                selectedItems.delete(position);
                            } else {
                                ////문제x///
                                // 직전의 클릭됐던 Item의 클릭상태를 지움
                                selectedItems.delete(preposition);
                                // 클릭한 Item의 position을 저장
                                selectedItems.put(position, true);
                            }
                            // 해당 포지션의 변화를 알림
                            if (preposition != -1) {
                                notifyItemChanged(preposition);
                            }
                            notifyItemChanged(position);//리스트 갱신
                            // 클릭된 position 저장
                            preposition = position;
                            break;
        ```
        
        ```java
        void changeVisibility(final boolean isExpanded) {
                    int dpValue = 50;
                    float d = context.getResources().getDisplayMetrics().density;
                    int height = (int) (dpValue * d);
        
                    ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0); //고치
                    va.setDuration(500);
                    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            calllayout.getLayoutParams().height = (int) valueAnimator.getAnimatedValue();
                            calllayout.requestLayout();
                            calllayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                        }
                    });
                    va.start();
        ```
        
        more  버튼을 누르면 해당 연락처를 modify, delete할 수 있도록 레이아웃이 하나 더 나타난다. 이 레이아웃의 구현은 setvisibility를 이용해서 구현했다. more 버튼을 누른 상태에서 다른 아이템을 누르면 modify/delete 레이아웃도 함께 사라져야 하므로, 이를 구분할 수 있는 reference로 boolean  타입인 check을 사용한다.
        
        ```java
        case R.id.morebutton:
                            if (check == false){
                                option.setVisibility(option.VISIBLE);
                                check = true;
                            }
                            else{
                                option.setVisibility(option.GONE);
                                check = false;
                            }
                            break;
        ```
        
        
    - Delete
        
        Delete를 누르게 되면 ViewHolder의 TextView에 있는 name과 number를 string으로 가져온다. 그 후 해당 name의 id를 찾는다. cursor와 for loop을 사용하며 Textview의 name과 Contacts.db의 이름을 비교한다. 같은 값을 찾게 되면 db 테이블에서 삭제한다.
        
        ```java
        case R.id.delete:
                            //activity.getContentResolver().delete(Uri.parse(MainActivity.PROVIDER_URI),"name="+name.getText().toString() ,null);
        
                            String[] columns = new String[]{"_id", "name", "phone"};
                            Cursor c = activity.getContentResolver().query(Uri.parse(MainActivity.PROVIDER_URI),columns, null,null,null,null);
                            int id = 0;
        
                            if(c != null){
                                while(c.moveToNext()){
                                    String find_name = c.getString(1);
                                    id = c.getInt(0);
                                    System.out.println(find_name);
                                    System.out.println(mname);
                                    if(mname.equals(find_name)){
                                        System.out.println("it's same\n");
                                        //System.out.println(id);
                                        break;
                                    }
                                }
                            }
                            activity.getContentResolver().delete(Uri.parse(MainActivity.PROVIDER_URI),"_id ="+ String.valueOf(id) ,null);
                            Intent intent1 = new Intent(context, MainActivity.class);
                            activity.startActivity(intent1);
                            break;
                    }
        ```
        ![ezgif com-gif-maker](https://user-images.githubusercontent.com/32477937/148048930-7e3fe9d3-c03e-4c9c-ad09-15dd55419570.gif)
        
    - Call / SMS
        
        버튼을 클릭하면 안드로이드에서 제공하는 기본 서비스를 사용하여 전화와 메세지를 할 수 있다.
        
        ```java
        case R.id.callbutton:
                            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CALL);
                            }
                            context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+m_number)));
                            break;
                        case R.id.messagebutton:
                            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_SMS);
                            }
                            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:"+m_number)));
                            break;
        ```
        ![ezgif com-gif-maker](https://user-images.githubusercontent.com/32477937/148049852-c1a8ec53-30a4-493b-a524-2b4bf1935e7d.gif)

        

# Second Tap: Gallery
:heavy_check_mark: tab2는 Android 기기의 갤러리에 존재하는 모든 사진 파일을 보여주며,

:heavy_check_mark: 사진을 클릭했을 때 선택된 사진의 원본을 볼 수 있고,

:heavy_check_mark: 이미지 Zoon-in & Zoom-out 이 가능하도록 구현하였다.
### 1. **갤러리 구현**

- `RecyclerView`와 `GridLayout` 사용하여 레이아웃을 구현. 안드로이드 이미지 Uri로 파일을 얻기 위해 ContentResolver를 이용해 이미지 경로를 받아서 ArrayList 에 담았으며, `[Glide` 라이브러리](https://github.com/bumptech/glide) 사용하여 이미지를 load 하는 방식으로 갤러리를 구현하였다.

<img src=https://user-images.githubusercontent.com/43024179/148047970-211009ed-bf8e-48a3-b9a1-36b0ad1548ed.gif)

### 2. 선택한 이미지 크게 보여주기

- GalleryAdapter에서 Intent를 활용해 선택한 이미지를 ImageActivity로 전달하였고, Glide를 이용해 이미지를 보여주었다.

![선택사진보여주기](https://user-images.githubusercontent.com/43024179/148047965-797c9e9d-d52c-4a8b-bf8e-8c75b4b932cd.gif width="200" height="400"/>

### 3. 사진 Zoom-in & out

- [PhotoView](https://github.com/Baseflow/PhotoView) 라이브러리를 활용하여 사진을 확대 및 축소할 수 있는 기능을 추가했다.

![줌인아웃](https://user-images.githubusercontent.com/43024179/148047973-7a97af65-2a57-4519-a1fd-b5ee14801a94.gif)


# Third Tap: MBTI Test

- Component
    - Fragment
        
        MBTI test는 총 12개의 질문으로 구성되어 있다. 질문들의 화면은 모두 fragment로 구성되어 있다.
        
        ```java
        phonebookFragment = new PhonebookFragment();
                galleryFragment = new GalleryFragment();
                mbtiMain = new MbtiMain();
                mbtiQ1 = new MbtiQ1();
                mbtiQ2 = new MbtiQ2();
                mbtiQ3 = new MbtiQ3();
                mbtiQ4 = new MbtiQ4();
                mbtiQ5 = new MbtiQ5();
                mbtiQ6 = new MbtiQ6();
                mbtiQ7 = new MbtiQ7();
                mbtiQ8 = new MbtiQ8();
                mbtiQ9 = new MbtiQ9();
                mbtiQ10 = new MbtiQ10();
                mbtiQ11 = new MbtiQ11();
                mbtiQ12 = new MbtiQ12();
                mbtiResult = new MbtiResult();
                mymbti = new MBTI();
        ```
        
    - Interface
        
        해당 사용자의 MBTI 답변에 대한 데이터를 저장, 관리하기 위해 MBTI라는 인터페이스 클래스를 생성했다. 모든 질문이 완료되면 새로운 오브젝트가 생성되어, 테스트를 다시 시작할 수 있도록 구현했다.
        
        ```java
        public class MBTI {
            public int[] EI;
            public int[] SN;
            public int[] TF;
            public int[] PJ;
            public int mbti_page;
            String mbtitype;
            public Boolean backward;
        
            public MBTI(){
                EI = new int[] {0,0};
                SN = new int[] {0,0};
                TF = new int[] {0,0};
                PJ = new int[] {0,0};
                mbti_page = 0;
                mbtitype = null;
                backward = false;
            }
        
            public void managing_data(String type, String check){
                if(check == "Do"){
                    switch (type){
                        case "E":
                            this.EI[0]++;
                            break;
                        case "I":
                            this.EI[1]++;
                            break;
                        case "S":
                            this.SN[0]++;
                            break;
                        case "N":
                            this.SN[1]++;
                            break;
        ......
        ```
        
    - KakaoLink(Kakao API)
        
        카카오톡 공유하기 기능을 구현하기위해 kakaoLink를 사용한다. kakao sdk v2 이상부터는 java에서  kakao link를 지원하지 않기 때문에 직접 구현해야한다.
        
        ```java
        implementation "com.kakao.sdk:v2-link:2.8.4" // 메시지(카카오링크)
        ```
        
- Function
    - Forward / Backward
        
        모든 데이터는 MBTI 클래스에서 관리한다. managin_data 메소드는 check 인자를 통해 데이터를 추가할 것인지, 삭제할 것인지 결정한다. 
        
        ```java
        public void managing_data(String type, String check){
                if(check == "Do"){
        ...
        				}
        				else{
        						if(check == "Undo"){
        					     switch (type){
        						        case "E":
        ...
        ```
        
        질문이 넘어간 상태에서 되돌아갈 때, 전 질문에서 선택했던 데이터를 삭제해주어야한다. 되돌아가기 버튼을 누르면 mainactivity에 정의된 backward를 true로 설정하고 replacement를 한다. 그 후 직전 질문의 fragment의 onCreateView에서 데이터를 지워준다.
        
        ```java
        @Override
            public void onClick(View view){
                switch (view.getId()){
                    case R.id.Q7A1:
                        activity.mymbti.managing_data("S", "Do");
                        pastselection = "S";
                        break;
                    case R.id.Q7A2:
                        activity.mymbti.managing_data("N", "Do");
                        pastselection = "N";
                        break;
                    case R.id.button7:
                        activity.mymbti.backward = true;
                        activity.replaceFragment(activity.mbtiQ6);
                        return;
                }
                activity.replaceFragment(activity.mbtiQ8);
            }
        }
        ```
        
        ```java
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
         ....
                if(activity.mymbti.backward == true){
                    activity.mymbti.managing_data(pastselection,"Undo");
                    System.out.println("Undo\n");
                    activity.mymbti.backward = false;
                }
        .....
                activity.mymbti.mbti_page = 7;
                return rootView;
        ```
        
        12개의 질문이 끝나기 전에 다른 탭으로 이동했다가 다시 mbti로 돌아오는 경우, 테스트가 계속 진행되어야한다. MBTI 클래스 내부에 mbti_page 변수로 각 질문 번호를 저장해둔다. 다른 탭을 이동했다가 돌아오는 경우, switch case를 통해 테스트가 끝나지 않고 직전 질문의 fragment로 화면을 replace한다. 2번의 체크를 통해 결과화면에서 다른탭으로 이동했다가 돌아오더라도 결과화면은 유지되고, 해당 탭 버튼을 한번 더 눌러야 테스트가 다시 시작되도록 구현했다. 결과의 이미지는 모두 image hosting
        
        ```java
        private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.item_phonebook:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, phonebookFragment).commit();
                            second_check = true;
                            break;
                        case R.id.item_gallery:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, galleryFragment).commit();
                            second_check = true;
                            break;
                        case R.id.item_calendar:
                            System.out.println(mymbti.mbti_page);
                            switch (mymbti.mbti_page) {
                                case 0:
                                    mymbti = new MBTI();
                                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiMain).commit();
                                    break;
                               ......
                                case 13:
                                    if(tap3_check == false | second_check == true){
                                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiResult).commit();
                                        second_check = false;
                                    }
                                    else{
                                        mymbti = new MBTI();
                                        tap3_check = false;
                                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiMain).commit();
                                    }
                                    break;
                            }
        
                    }
                    return tr
                }
        ```
        
    - 카카오톡 공유하기
        
        kakao sdk v2 부터 java에서 kakao link를 지원하지않는다. 따라서 카카오 dev에서 해당 앱의 각 키들을 설정해주고 kakaolink함수를 구현해야한다. 공유하기 이미지로 전송될 이미지의 url이 필요하다. 테스트 결과 이미지들은 모두 무료 호스팅 사이트인 imgur.com에 업로드 시켜놓았다.
        
        ```java
        public void kakaolink() {
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
        ....
        
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
        ```
