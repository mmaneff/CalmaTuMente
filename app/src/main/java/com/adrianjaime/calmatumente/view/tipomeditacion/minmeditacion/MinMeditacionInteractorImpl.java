package com.adrianjaime.calmatumente.view.tipomeditacion.minmeditacion;


import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by emaneff on 09/01/2017.
 */
public class MinMeditacionInteractorImpl implements MinMeditacionInteractor {

    protected ProgressBarTask _pbTask;

    @Override
    public void startMeditacion(OnMeditacionFinishedListener listener) {
        _pbTask = new ProgressBarTask();
        _pbTask.execute(60);
    }


    class ProgressBarTask extends AsyncTask<Integer, Integer, String> {

        int preparate = 3;
        int inhala = 0;
        int reten = 0;
        int exhala = 0;
        int progress = 0;

        @Override
        protected String doInBackground(Integer... params) {
            Log.i("params", Integer.toString(params[0]));
            for (int count = 0; count <= params[0]; count++) {
                try {
                    Thread.sleep(1000);
                    Log.i("doInBackground", Integer.toString(count));
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Preparate";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //TODO
            //tvEstado.setText(result);
            //tvCuenta.setText("3");
            //mProgress.setProgress(0);
            //btnPlay.setEnabled(true);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //TODO
            //tvEstado.setText("Starting");
            //mProgress.setProgress(0);
            //btnPlay.setEnabled(false);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i("onProgressUpdate", Integer.toString(values[0]));

            //tvEstado.setText(getTexto(values[0]));
            //tvCuenta.setText(getCuenta(values[0]));

            if (values[0] >= 3) {
                //progress = (values[0] == 22) ? 0 : progress;
                if ((values[0] == 22) || (values[0] == 41)) {
                    inhala = 0;
                    reten = 0;
                    exhala = 0;
                    progress = 0;
                    //mProgress.setProgress(0);
                }

                //changeProgressColor(progress);
                //mProgress.setProgress(progress);
                progress = progress + 1;
            }
        }

        /**
         * @param value
         * @return
         */
        private String getTexto(Integer value) {
            String texto = "";

            if (value < 3) {
                texto = "Preparate";
            } else if (value < 7) {
                texto = "Inhala";
            } else if (value < 14) {
                texto = "Reten";
            } else if (value < 22) {
                texto = "Exhala";
            } else if (value < 26) {
                texto = "Inhala";
            } else if (value < 33) {
                texto = "Reten";
            } else if (value < 41) {
                texto = "Exhala";
            } else if (value < 45) {
                texto = "Inhala";
            } else if (value < 52) {
                texto = "Reten";
            } else if (value < 60) {
                texto = "Exhala";
            }

            return texto;
        }


        private String getCuenta(Integer value) {
            String cuenta = "";

            if (value < 3) {
                preparate = preparate - 1;
                cuenta = Integer.toString(preparate);
            } else if (value < 7) {
                inhala = inhala + 1;
                cuenta = Integer.toString(inhala);
            } else if (value < 14) {
                reten = reten + 1;
                cuenta = Integer.toString(reten);
            } else if (value < 22) {
                exhala = exhala + 1;
                cuenta = Integer.toString(exhala);
            } else if (value < 26) {
                inhala = inhala + 1;
                cuenta = Integer.toString(inhala);
            } else if (value < 33) {
                reten = reten + 1;
                cuenta = Integer.toString(reten);
            } else if (value < 41) {
                exhala = exhala + 1;
                cuenta = Integer.toString(exhala);
            } else if (value < 45) {
                inhala = inhala + 1;
                cuenta = Integer.toString(inhala);
            } else if (value < 52) {
                reten = reten + 1;
                cuenta = Integer.toString(reten);
            } else if (value < 60) {
                exhala = exhala + 1;
                cuenta = Integer.toString(exhala);
            }

            if(preparate == 0 || inhala == 4 || reten == 7 || exhala == 8) {
                //timbre.start();
            }

            return cuenta;
        }


    }


}
