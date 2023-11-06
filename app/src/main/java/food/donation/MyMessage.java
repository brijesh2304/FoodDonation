package food.donation;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

public class MyMessage {
	Context context;
	public MyMessage(){}
	public MyMessage(Context context){
		this.context=context;
	}
	public void myMsg(String title,String message){
		Builder b=new Builder(context);
		b.setTitle(title);
		b.setMessage(message);
		b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		b.show();
	}
}
