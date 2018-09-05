package watted.imep.Chat;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import watted.imep.R;

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {
    ImageView photoImageView;
    TextView messageTextView;
    LinearLayout LinearLayout;
    TextView authorTextView;
    FriendlyMessage message;
    String User;

    public MessageAdapter(Context context, int resource, List<FriendlyMessage> objects, FirebaseUser currentUser) {
        super(context, resource, objects);
        User = currentUser.getDisplayName();

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = ((Activity) getContext())
                    .getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }
        LinearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout);

        photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);

        authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);

        message = getItem(position);

        messageTextView.setBackgroundResource(R.drawable.rpink);
        LinearLayout.setGravity(Gravity.LEFT);

        if (User != message.getName()) {

            messageTextView.setBackgroundResource(R.drawable.g);
            LinearLayout.setGravity(Gravity.RIGHT);

        }
        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            messageTextView.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext())
                    .load(message.getPhotoUrl())
                    .into(photoImageView);
        } else {

            messageTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            messageTextView.setText(message.getText());

        }


        authorTextView.setText(message.getName());

        return convertView;
    }

}
