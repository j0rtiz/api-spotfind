import React from 'react';
import { KeyboardAvoidingView, StyleSheet, Text, View, Image, TouchableOpacity } from 'react-native';

import maca from './assets/maca.png';

export default function App({ navigation }) {
  return (
    <KeyboardAvoidingView style={styles.container}> 

      <View >
        <Text style={styles.text}>Aperte o botão</Text>
      </View>

      <TouchableOpacity>
        <Text>Clique aqui</Text>
      </TouchableOpacity>
      
    </KeyboardAvoidingView>
  );
}

const styles = StyleSheet.create({

  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },

  text: {
    fontSize: 20,
    height: 100
  }
});

