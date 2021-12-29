package com.camp.project1.ui.phonebook;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.camp.project1.Data;
import com.camp.project1.MainActivity;
import com.camp.project1.R;
import com.camp.project1.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PhonebookFragment extends Fragment {

    public RecyclerAdapter recyclerAdapter;
    public Context ct;
    private ArrayList<Data> dataset;


    @Nullable
    @org.jetbrains.annotations.Nullable

    public ArrayList<Data> getContacts(Context context){
        ArrayList<Data> dataset = new ArrayList<>();

        ContentResolver resolver = context.getContentResolver();

        // 2. 전화번호가 저장되어 있는 테이블 주소값(Uri)을 가져오기
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // 3. 테이블에 정의된 칼럼 가져오기
        // ContactsContract.CommonDataKinds.Phone 이 경로에 상수로 칼럼이 정의
        String[] projection = { ContactsContract.CommonDataKinds.Phone.CONTACT_ID // 인덱스 값, 중복될 수 있음 -- 한 사람 번호가 여러개인 경우
                ,  ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                ,  ContactsContract.CommonDataKinds.Phone.NUMBER};

        // 4. ContentResolver로 쿼리를 날림 -> resolver 가 provider 에게 쿼리하겠다고 요청
        Cursor cursor = resolver.query(phoneUri, projection, null, null, null);

        // 4. 커서로 리턴된다. 반복문을 돌면서 cursor 에 담긴 데이터를 하나씩 추출
        if(cursor != null){
            while(cursor.moveToNext()){
                // 4.1 이름으로 인덱스를 찾아준다
                int idIndex = cursor.getColumnIndex(projection[0]); // 이름을 넣어주면 그 칼럼을 가져와준다.
                int nameIndex = cursor.getColumnIndex(projection[1]);
                int numberIndex = cursor.getColumnIndex(projection[2]);
                // 4.2 해당 index 를 사용해서 실제 값을 가져온다.
                String id = cursor.getString(idIndex);
                String name = cursor.getString(nameIndex);
                String number = cursor.getString(numberIndex);

                Data data = new Data();
                data.setId(id);
                data.setName(name);
                data.setNumber(number);

                dataset.add(data);
            }
        }
        // 데이터 계열은 반드시 닫아줘야 한다.
        cursor.close();
        return dataset;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View fragmentphonebook = inflater.inflate(R.layout.fragment_phonebook, container, false);

        RecyclerView recyclerView = fragmentphonebook.findViewById(R.id.recyclerview);

        ct = container.getContext();//fragment에서 동작할 수 있게 해주는 것.

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ct);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        dataset = getContacts(ct);
        recyclerAdapter.addlist(dataset);
        recyclerAdapter.notifyDataSetChanged();

        return fragmentphonebook;
    }

}