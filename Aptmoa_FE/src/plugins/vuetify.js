import Vue from 'vue'
import Vuetify from 'vuetify/lib'



Vue.use(Vuetify)

export default new Vuetify({
  theme: {
    themes: {
      light: {
        primary: '#364A73',
        secondary: '#083759',
        background_light: '#F2F2F2',
        text_light_lemon: '#F0F4C3',
        text_light_light_lemon:'#FDFFE6',
        warning: '#8A3B3A',
        background_white:'#FCFCFC',
      },
    },
  },
})
