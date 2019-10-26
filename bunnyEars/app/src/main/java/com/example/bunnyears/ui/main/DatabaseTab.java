package com.example.bunnyears.ui.main;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.bunnyears.R;

import java.util.Random;

//import androidx.appcompat.app.AppCompatActivity ;

public class DatabaseTab extends Fragment {
    Button buttonStart, buttonStop, buttonPlayLastRecordAudio,
            buttonStopPlayingRecording;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder;
    Random random;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.databasetab, container, false);
        return view;
    }
}

//        buttonStart =  getView().findViewById(R.id.button);
//        buttonStop =  getView().findViewById(R.id.button2);
//        buttonPlayLastRecordAudio = getView().findViewById(R.id.button3);
//        buttonStopPlayingRecording = getView().findViewById(R.id.button4);
//
//        buttonStop.setEnabled(false);
//        buttonPlayLastRecordAudio.setEnabled(false);
//        buttonStopPlayingRecording.setEnabled(false);
//
//        random = new Random();
//
//        buttonStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(checkPermission()) {
//
//                    AudioSavePathInDevice =
//                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
//                                    CreateRandomAudioFileName(5) + "AudioRecording.3gp";
//
//                    MediaRecorderReady();
//
//                    try {
//                        mediaRecorder.prepare();
//                        mediaRecorder.start();
//                    } catch (IllegalStateException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//
//                    buttonStart.setEnabled(false);
//                    buttonStop.setEnabled(true);
//
//                    Toast.makeText(getActivity(), "Recording started",
//                            Toast.LENGTH_LONG).show();
//                } else {
//                    requestPermission();
//                }
//
//            }
//        });
//
//        buttonStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mediaRecorder.stop();
//                buttonStop.setEnabled(false);
//                buttonPlayLastRecordAudio.setEnabled(true);
//                buttonStart.setEnabled(true);
//                buttonStopPlayingRecording.setEnabled(false);
//
//                Toast.makeText(getActivity(), "Recording Completed",
//                        Toast.LENGTH_LONG).show();
//            }
//        });
//
//        buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) throws IllegalArgumentException,
//                    SecurityException, IllegalStateException {
//
//                buttonStop.setEnabled(false);
//                buttonStart.setEnabled(false);
//                buttonStopPlayingRecording.setEnabled(true);
//
//                mediaPlayer = new MediaPlayer();
//                try {
//                    mediaPlayer.setDataSource(AudioSavePathInDevice);
//                    mediaPlayer.prepare();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                mediaPlayer.start();
//                Toast.makeText(getActivity(), "Recording Playing",
//                        Toast.LENGTH_LONG).show();
//            }
//        });
//
//        buttonStopPlayingRecording.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                buttonStop.setEnabled(false);
//                buttonStart.setEnabled(true);
//                buttonStopPlayingRecording.setEnabled(false);
//                buttonPlayLastRecordAudio.setEnabled(true);
//
//                if(mediaPlayer != null){
//                    mediaPlayer.stop();
//                    mediaPlayer.release();
//                    MediaRecorderReady();
//                }
//            }
//        });
//        return view;
//    }
//
//    public void MediaRecorderReady(){
//        mediaRecorder=new MediaRecorder();
//        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
//        mediaRecorder.setOutputFile(AudioSavePathInDevice);
//    }
//
//    public String CreateRandomAudioFileName(int string){
//        StringBuilder stringBuilder = new StringBuilder( string );
//        int i = 0 ;
//        while(i < string ) {
//            stringBuilder.append(RandomAudioFileName.
//                    charAt(random.nextInt(RandomAudioFileName.length())));
//
//            i++ ;
//        }
//        return stringBuilder.toString();
//    }
//
//    private void requestPermission() {
//        ActivityCompat.requestPermissions(getActivity(), new
//                String[]{RECORD_AUDIO}, RequestPermissionCode);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case RequestPermissionCode:
//                if (grantResults.length> 0) {
//                    boolean StoragePermission = grantResults[0] ==
//                            PackageManager.PERMISSION_GRANTED;
//                    boolean RecordPermission = grantResults[1] ==
//                            PackageManager.PERMISSION_GRANTED;
//
//                    if (StoragePermission && RecordPermission) {
//                        Toast.makeText(getActivity(), "Permission Granted",
//                                Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(getActivity(),"Permission Denied",Toast.LENGTH_LONG).show();
//                    }
//                }
//                break;
//        }
//    }
//
//    public boolean checkPermission() {
//        int result = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), RECORD_AUDIO);
//        return result == PackageManager.PERMISSION_GRANTED;
//    }
//}
