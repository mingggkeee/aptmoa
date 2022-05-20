<template>
  <v-container id="contact" tag="section">
    <base-card color="background_light" class="pa-3">
      <v-container>
        <v-row>
          <v-col cols="12" md="6" offset-md="1">
            <base-subheading>Contact</base-subheading>

            <v-list class="transparent">
              <v-list-item>
                <v-list-item-avatar color="primary">
                  <v-icon class="elevation-4" dark>
                    mdi-email-outline
                  </v-icon>
                </v-list-item-avatar>

                <v-list-item-title>EMAIL</v-list-item-title>

                <v-list-item-subtitle>
                  gpfls0506@gmail.com & mksu234@gmail.com
                </v-list-item-subtitle>
              </v-list-item>

              <v-list-item>
                <v-list-item-avatar color="primary">
                  <v-icon class="elevation-4" dark>
                    mdi-map-marker
                  </v-icon>
                </v-list-item-avatar>

                <v-list-item-title>ADDRESS</v-list-item-title>

                <v-list-item-subtitle>
                  1234 WORLD DR. HERE, TX 76123
                </v-list-item-subtitle>
              </v-list-item>

              <v-list-item>
                <v-list-item-avatar color="primary">
                  <v-icon class="elevation-4" dark>
                    mdi-phone
                  </v-icon>
                </v-list-item-avatar>

                <v-list-item-title>PHONE</v-list-item-title>

                <v-list-item-subtitle>
                  555-789-1234
                </v-list-item-subtitle>
              </v-list-item>
            </v-list>
          </v-col>
          <v-col cols="12" md="4">
            <tags />
          </v-col>
        </v-row>
      </v-container>
    </base-card>
  </v-container>
</template>

<script>
export default {
  name: "HomeContact",

  components: {
    Tags: () => import("@/components/Tags")
  }
};
</script>
